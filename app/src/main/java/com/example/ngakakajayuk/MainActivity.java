package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable animation;
    MediaPlayer mediaPlayer;
    MediaPlayer click;
    ImageButton startGame;
    TextView title;

    Handler handler = new Handler();
    final static int DELAY = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);


        startGame = findViewById(R.id.btn_start_game);
        title = findViewById(R.id.title);

        ImageView image = findViewById(R.id.image_anim);
       /* image.setBackgroundResource(R.drawable.animation_menu);

        animation = (AnimationDrawable) image.getBackground();

        animation.start();*/

        click = MediaPlayer.create(this, R.raw.click_effect);

        mediaPlayer = MediaPlayer.create(this, R.raw.music_game);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                startGame.setVisibility(View.VISIBLE);
                startGame.startAnimation(animScale);

                title.setVisibility(View.VISIBLE);
                title.setAnimation(animFade);

            }
            //your code start with delay in one second after calling this method
        }, DELAY);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                Intent i = new Intent(MainActivity.this,GameActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();

        super.onDestroy();

    }
}
