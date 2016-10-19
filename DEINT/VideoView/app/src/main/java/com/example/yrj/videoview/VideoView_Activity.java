package com.example.yrj.videoview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Application that play a video, and restore the position when screen rotates
 * @author Yeray Ruiz
 * @version 1.0
 */
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

    /**
     * Restoring the video time position and if it was playing, it is resume
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Setting the last position
        videoView.seekTo(savedInstanceState.getInt("position"));
        //Pause the video if it was not playing before
        boolean playing = getIntent().getExtras().getBoolean("playing");
        if(!playing){
            videoView.pause();
        }
    }

    /**
     * Saving the video time position and if it was playing
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Saving the current video position
        int position = videoView.getCurrentPosition();
        outState.putInt("position",position);
        //Saving if it was playing
        boolean playing = getIntent().getExtras().getBoolean("playing");
        if(!playing){
            videoView.pause();
        }
    }

    /**
     * Pausing the video
     */
    @Override
    protected void onPause() {
        super.onPause();
        boolean playing = videoView.isPlaying();
        getIntent().putExtra("playing", playing);
        if(!playing){
            videoView.pause();
        }
    }

    /**
     * Starting the video
     */
    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    /**
     * Resuming the video
     */
    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    /**
     * Stopping the video
     */
    @Override
    protected void onStop() {
        super.onStop();
        videoView.suspend();
    }
}
