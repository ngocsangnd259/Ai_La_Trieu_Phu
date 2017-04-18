package com.example.ngocsang.ailatrieuphu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ngocsang.ailatrieuphu.R;
import com.example.ngocsang.ailatrieuphu.dialog.InfoDialog;
import com.example.ngocsang.ailatrieuphu.model.MusicPlayer;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private ImageView imgBGCircle;
    private ImageView btnPlay;
    private ImageView btnVolume;
    private MusicPlayer bgMusic;

    private void initializeComponents() {
        imgBGCircle = (ImageView) findViewById(R.id.img_bg_circle_anim);
        btnPlay = (ImageView) findViewById(R.id.btn_play);
        btnVolume = (ImageView) findViewById(R.id.btn_volume);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_bg_circle);
        imgBGCircle.startAnimation(animation);
        btnPlay.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        findViewById(R.id.btn_info).setOnClickListener(this);
        MusicPlayer.setMute(false);
    }

    private void setVolume() {
        if (!MusicPlayer.getMute()) {
            bgMusic.stop();
            MusicPlayer.setMute(true);
            btnVolume.setImageResource(R.drawable.volume_off);
        } else {
            MusicPlayer.setMute(false);
            bgMusic = new MusicPlayer(R.raw.bgmusic);
            bgMusic.loop();
            btnVolume.setImageResource(R.drawable.volume_on);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgMusic = new MusicPlayer(R.raw.bgmusic);
        bgMusic.loop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgMusic.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play: {
                MusicPlayer touch = new MusicPlayer(R.raw.touch_sound);
                touch.play();
                Intent intent = new Intent(this, PlayActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btn_info: {
                InfoDialog infoDialog = new InfoDialog(this);
                infoDialog.show();
                break;
            }

            case R.id.btn_volume: {
                setVolume();
                break;
            }

            default: {
                break;
            }
        }
    }
}
