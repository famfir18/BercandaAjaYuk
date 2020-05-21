package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    ImageView ivTitle;
    @BindView(R.id.layout_nickname)
    RelativeLayout layoutNickname;
    @BindView(R.id.et_nickname)
    EditText etNickname;

    AnimationDrawable animation;
    MediaPlayer mediaPlayer;
    MediaPlayer click;
    ImageButton confirm;

    Handler handler = new Handler();
    final static int DELAY = 500;

    Handler nickname = new Handler();
    final static int DELAYS = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final Animation animScaleTitle = AnimationUtils.loadAnimation(this, R.anim.anim_scale_title);
        final Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);

        confirm = findViewById(R.id.btn_confirm);

        ImageView image = findViewById(R.id.image_anim);
       /* image.setBackgroundResource(R.drawable.animation_menu);

        animation = (AnimationDrawable) image.getBackground();

        animation.start();*/

        click = MediaPlayer.create(this, R.raw.click_effect);

        mediaPlayer = MediaPlayer.create(this, R.raw.music_game);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //your code start with delay in one second after calling this method
        handler.postDelayed(() -> {
            ivTitle.setVisibility(View.VISIBLE);
            ivTitle.startAnimation(animScaleTitle);
        }, DELAY);

        nickname.postDelayed(() -> {
            layoutNickname.setVisibility(View.VISIBLE);
            layoutNickname.setAnimation(animFade);
        }, DELAYS);




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                if (etNickname.getText().length() < 5) {
                    Snackbar.make(v, "Masukkan minimal 5 karakter", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Intent i = new Intent(MainActivity.this,MenuActivity.class);
                    i.putExtra("nickName", etNickname.getText().toString());
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
//        medPlayer.stop();
//        mediaPlayer.release();

        super.onDestroy();

    }

    @Override
    public void onPause() {
        mediaPlayer.stop();
        mediaPlayer.release();

        super.onPause();

    }
}
