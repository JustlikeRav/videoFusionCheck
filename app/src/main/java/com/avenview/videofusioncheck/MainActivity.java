package com.avenview.videofusioncheck;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView1, videoView2;

        videoView1 = findViewById(R.id.vv1);
        videoView2 = findViewById(R.id.vv2);

        String  UrlPath="android.resource://"+getPackageName()+"/"+R.raw.videohexcode;
        videoView1.setVideoURI(Uri.parse(UrlPath));
//        videoView1.start();

        videoView2.setVideoURI(Uri.parse(UrlPath));
        videoView2.start();
    }
}
