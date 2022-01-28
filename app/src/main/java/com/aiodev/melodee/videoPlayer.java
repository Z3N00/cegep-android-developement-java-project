package com.aiodev.melodee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class videoPlayer extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private ArrayList<Video> videoList = new ArrayList<>();
    private VideoAdapter adapter;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        firestore = FirebaseFirestore.getInstance();
        viewPager2 = findViewById(R.id.viewPager2);
        setData();
/*
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.a ,"Reel 1","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.b ,"Reel 2","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.c ,"Reel 3","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.d ,"Reel 4","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.e ,"Reel 5","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.f ,"Reel 6","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.g ,"Reel 7","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.h ,"Reel 8","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.i ,"Reel 9","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.j ,"Reel 10","Reels are short, entertaining videos on Instagram where you can express your creativity and bring your brand to life"));
*/


    }

    public void setData(){
        firestore.collection("videos").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {

                            String name = d.getData().get("audio").toString();

                            String des = d.getData().get("caption").toString();
                            String videoUrl = d.getData().get("video").toString();
                            String userId = d.getData().get("userID").toString();
                            videoList.add(new Video(name, des, userId, videoUrl));
                        }
                    } else {
                        //Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                setAdapter();
            }
        });

    }

    public void setAdapter(){
        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);
    }
}