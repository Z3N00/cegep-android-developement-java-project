package com.aiodev.melodee;

import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoviewHolder> {

    List<Video> videoList;

    public VideoAdapter(List<Video> videoList){
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_video,parent,false);
        return new VideoviewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull VideoviewHolder holder, int position) {
        holder.setVideoData(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoviewHolder extends RecyclerView.ViewHolder{

        VideoView videoView;
        TextView title, desc;

        public VideoviewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoView);
            title = itemView.findViewById(R.id.video_title);
            desc = itemView.findViewById(R.id.video_desc);
        }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        public void setVideoData(Video video){
            title.setText(video.getAudio());
            Log.d("name", video.getAudio());
            desc.setText(video.getCaption());
            Log.d("Video Url", video.getVideo());
            videoView.setVideoPath(video.getVideo());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                    float scale = videoRatio / screenRatio ;
                    if(scale >= 1f){
                        videoView.setScaleX(scale);
                    }else{
                        videoView.setScaleY(1f/scale);
                    }
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}