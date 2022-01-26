package com.aiodev.melodee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class videoPlayer extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private List<Video> videoList;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoList = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

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


        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);
    }
}