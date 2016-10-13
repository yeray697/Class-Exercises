package com.example.yrj.videoview;

import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoView_Activity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoView = (VideoView) findViewById(R.id.videoView);
        String UrlPath="android.resource://"+getPackageName()+"/"+R.raw.llamasong;
        videoView.setVideoURI(Uri.parse(UrlPath));
        videoView.start();
        videoView.start();
    }
}
