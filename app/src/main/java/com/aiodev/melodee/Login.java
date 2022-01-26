package com.aiodev.melodee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button signup, loginbtn;
    EditText usernameL, passwordL;
    TextView forgotpass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.signupbtl);
        usernameL = findViewById(R.id.usernamel);
        passwordL = findViewById(R.id.passwordl);
        loginbtn = findViewById(R.id.loginbtnl);
        forgotpass = findViewById(R.id.forgotpass);
        mAuth = FirebaseAuth.getInstance();



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Forgot password Via Email", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, ForgotPass.class));
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
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

    private void loginUser(){
        String email = usernameL.getText().toString();
        String password = passwordL.getText().toString();

        if (TextUtils.isEmpty(email)){
            usernameL.setError("Email cannot be empty");
            usernameL.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordL.setError("Password cannot be empty");
            passwordL.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else{
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void mainpage(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));
    }
}