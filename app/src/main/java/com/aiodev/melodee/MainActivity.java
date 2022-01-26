package com.aiodev.melodee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView videoplayer;
    ImageView settings;
    LinearLayout melodee;
    BottomNavigationView bottomNavigationView;
    ImageView image;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoplayer = findViewById(R.id.videoplayer);
        bottomNavigationView = findViewById(R.id.bottomnav);
        melodee = findViewById(R.id.melo);
        image = findViewById(R.id.imageclick);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,videoPlayer.class);
                startActivity(intent);
            }
        });

        melodee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,videoPlayer.class);
                startActivity(intent);
            }

        });

        videoplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,videoPlayer.class);
                startActivity(intent);
            }

        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:

                    break;

                case R.id.search:
                    Intent i= new Intent(MainActivity.this,Search.class);
                    startActivity(i);
                    break;

                case R.id.plus:
                    Intent in= new Intent(MainActivity.this,Camera.class);
                    startActivity(in);
                    break;

                case R.id.notification:
                    Intent inte= new Intent(MainActivity.this,Notification.class);
                    startActivity(inte);
                    break;

                case R.id.person:
                    Intent inten= new Intent(MainActivity.this, profile.class);
                    startActivity(inten);
                    break;
            }
            return true;
        });
    }
}