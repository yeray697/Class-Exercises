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
        mc = new MediaController(this);
        videoView.setMediaController(mc);
        String UrlPath="android.resource://"+getPackageName()+"/"+R.raw.video;
        videoView.setVideoURI(Uri.parse(UrlPath));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        videoView.seekTo(savedInstanceState.getInt("position"));
        if (!savedInstanceState.getBoolean("playing", false))
            videoView.pause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int position = videoView.getCurrentPosition();
        outState.putInt("position",position);
        if (outState.getBoolean("playing"))
            outState.putBoolean("playing",videoView.isPlaying());
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.suspend();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();

    }
    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //videoView.pause();
    }
}
