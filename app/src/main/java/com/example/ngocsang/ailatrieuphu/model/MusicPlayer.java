package com.example.ngocsang.ailatrieuphu.model;

import android.media.AudioManager;
import android.media.MediaPlayer;


import com.example.ngocsang.ailatrieuphu.App;

import java.util.Random;

/**
 * Created by NgocSang on 1/21/2017.
 */

public class MusicPlayer {
    public static final int IDLE = -1;
    public static final int PLAY = 1;
    public static final int LOOP = 2;
    public static final int PAUSE = 3;
    public static final int STOP = 4;

    private static boolean mute;

    private int state;
    private MediaPlayer mediaPlayer;

    public MusicPlayer(int resId) {
        mediaPlayer = MediaPlayer.create(App.getContext(), resId);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        state = IDLE;
    }

    public MusicPlayer(int resId[]) {
        Random r = new Random();
        mediaPlayer = MediaPlayer.create(App.getContext(), resId[r.nextInt(resId.length)]);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        state = IDLE;
    }

    public static void setMute(boolean mute) {
        MusicPlayer.mute = mute;
    }

    public static boolean getMute() {
        return mute;
    }

    public void play() {
        if (mute) return;
        if (state == IDLE) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
            mediaPlayer.start();
            state = PLAY;
        }
    }

    public void loop() {
        if (mute) return;
        if (state == IDLE || state == PAUSE) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            state = LOOP;
        }
    }

    public void stop() {
        if (mute) return;
        if (state == PLAY || state == LOOP || state == PAUSE) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                state = STOP;
            }
        }
    }
}
