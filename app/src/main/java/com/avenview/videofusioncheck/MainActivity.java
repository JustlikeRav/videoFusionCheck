package com.avenview.videofusioncheck;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;

import java.io.File;

public class MainActivity extends AppCompatActivity {

//    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleExoPlayerView exoPlayerView = findViewById(R.id.video_view_epv);
        lol(exoPlayerView);
        SimpleExoPlayerView exoPlayerView2 = findViewById(R.id.video_view_epv2);
        lol(exoPlayerView2);
    }

    private void lol(SimpleExoPlayerView exoPlayerView) {
        try {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(null));

            File nameFile = new File(getFilesDir().getAbsolutePath() + "/Content/", "14993b.mp4");
            Uri videoURI = Uri.fromFile(nameFile);

            DataSpec dataSpec = new DataSpec(videoURI);
            final FileDataSource fileDataSource = new FileDataSource();
            try {
                fileDataSource.open(dataSpec);
            } catch (FileDataSource.FileDataSourceException e) {
                e.printStackTrace();
            }

            DataSource.Factory factory = new DataSource.Factory() {
                @Override
                public DataSource createDataSource() {
                    return fileDataSource;
                }
            };

            MediaSource mediaSource = new ExtractorMediaSource(fileDataSource.getUri(), factory, new DefaultExtractorsFactory(),
                    null, null);

            LoopingMediaSource loopingSource = new LoopingMediaSource(mediaSource);
            exoPlayer.prepare(loopingSource);

            exoPlayerView.setPlayer(exoPlayer);

            exoPlayer.setPlayWhenReady(true);

            exoPlayer.addListener(new ExoPlayer.EventListener() {

                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {

                }

                @Override
                public void onPositionDiscontinuity() {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }
            });

        } catch (Exception e) {
            Log.e("MainAcvtivity", " exoplayer error " + e.toString());
        }
    }
}
