package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    //Set waktu lama splashscreen
    private static int splashInterval = 3000;
    private ProgressBar mProgress;

    ImageView logo;
    TextView tvLogo;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    final static int DELAY = 1000; // one second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.iv_logo_dev);
        tvLogo = findViewById(R.id.tv_logo_dev);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);

        logo.startAnimation(animScale);
        tvLogo.startAnimation(animFade);

        mediaPlayer = MediaPlayer.create(this, R.raw.whip);

        playAudioWithDelay();


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(i);

                //jeda selesai Splashscreen
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);
    }

    public void playAudioWithDelay(){
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                mediaPlayer.start();

            }
            //your code start with delay in one second after calling this method
        }, DELAY);
    }
}
