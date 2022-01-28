package com.aiodev.melodee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button loginbtn, signupbtn;
    EditText usernameS, passwordS , namecur;
    FirebaseAuth mAuth;
    ImageView gsignin;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firestore = FirebaseFirestore.getInstance();
        loginbtn = findViewById(R.id.loginbtn);
        usernameS = findViewById(R.id.username);
        passwordS = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signupbtn);
        namecur = findViewById(R.id.nameCur);
        gsignin = findViewById(R.id.gsignin);

        String name = namecur.getText().toString();


        mAuth = FirebaseAuth.getInstance();

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();

        gsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });





        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void createUser(){
        String email = usernameS.getText().toString();
        String password = passwordS.getText().toString();
        String name = namecur.getText().toString();

        if (TextUtils.isEmpty(email)){
            usernameS.setError("Email cannot be empty");
            usernameS.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordS.setError("Password cannot be empty");
            passwordS.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                        String uId = mFirebaseUser.getUid();
                        DocumentReference documentReference = firestore.collection("users").document(uId);

                        Map<String, String> user = new HashMap<>();
                        user.put("name", name);
                        user.put("email", email);

                        documentReference.set(user).addOnSuccessListener(unused -> {

                        }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show());
                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        updateUser();
                    }else{
                        Toast.makeText(Register.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void updateUser() {
        String name = namecur.getText().toString();
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        mAuth.getCurrentUser().updateProfile(changeRequest);
        mAuth.signOut();
        OpenLogin();
    }

    private void OpenLogin() {
        startActivity(new Intent(Register.this, Login.class));
        finish();
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(Register.this, MainActivity.class));
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }



}