package com.aiodev.melodee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    Button forgot, login;
    EditText resetET;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        auth = FirebaseAuth.getInstance();

        resetET = findViewById(R.id.resetpass);
        forgot = findViewById(R.id.resetbtn);
        login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPass.this, Login.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot();
            }
        });
    }
    private void forgot() {
        String email = resetET.getText().toString().trim();

        if(email.isEmpty()){
            resetET.setError("Email is required");
            resetET.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            resetET.setError("Please Provide Valid Email");
            resetET.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPass.this, "Check Your Email to reset Your pAssword !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPass.this, "try Again Something Wrong !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}