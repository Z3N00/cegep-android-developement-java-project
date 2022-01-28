package com.aiodev.melodee;

public class Video {
    private String audio, caption, userID, video;

    public Video(String audio, String caption, String userID, String video) {
        this.audio = audio;
        this.caption = caption;
        this.userID = userID;
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}