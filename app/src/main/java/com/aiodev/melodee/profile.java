package com.aiodev.melodee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    ImageView settings, twurl, yturl, igurl, fburl;
    TextView Uemail , Uname;
    ImageView logout;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        settings = findViewById(R.id.settings);
        twurl= findViewById(R.id.twitterurl);
        yturl= findViewById(R.id.yturl);
        igurl= findViewById(R.id.igurl);
        fburl= findViewById(R.id.fburl);
        Uemail = findViewById(R.id.emailuser);
        logout = findViewById(R.id.loginbtn);
        Uname = findViewById(R.id.nameuser);

        if(user != null){
            Uname.setText(user.getDisplayName());
            Uemail.setText(user.getEmail());
        }

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                auth.signOut();
//                startActivity(new Intent(profile.this, Login.class));
//                finish();
//            }
//        });




        twurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));
            }
        });

        yturl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));
            }
        });

        igurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));
            }
        });

        fburl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));
                auth.signOut();
                startActivity(new Intent(profile.this, Login.class));
                finish();
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, settings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null){
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }
}