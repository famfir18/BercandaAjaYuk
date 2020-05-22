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

import com.example.ngakakajayuk.Data.API.APIClient;
import com.google.android.material.snackbar.Snackbar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.picasso.Picasso;

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

       /* image.setBackgroundResource(R.drawable.animation_menu);

        animation = (AnimationDrawable) image.getBackground();

        animation.start();*/

       loadImage();

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
                if (etNickname.getText().length() == 0) {
                    Snackbar.make(v, "Jangan dikosongin", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (etNickname.getText().length() < 4 || etNickname.getText().length() > 16) {
                    Snackbar.make(v, "Nickname harus terdiri dari 4-16 karakter", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Intent i = new Intent(MainActivity.this,MenuActivity.class);
                    i.putExtra("nickName", etNickname.getText().toString());
                    startActivity(i);
                }
            }
        });

    }

    private void loadImage() {

        ImageView image = findViewById(R.id.image_anim);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                // Thread priority

                .threadPriority(Thread.NORM_PRIORITY)

                // Deny cache multiple image sizes on memory

                .denyCacheImageMultipleSizesInMemory()

                // Processing order like a stack (last in, first out)

                .tasksProcessingOrder(QueueProcessingType.LIFO)

                // Max image size to cache on memory

                .memoryCacheSize(1*1024*2014)

                // Max image size to cache on disc

                .diskCacheSize(2*1024*1024)

                // Write log messages

                .writeDebugLogs()

                .build();

        ImageLoader.getInstance().init(config);



        // Get ImageLoader instance

        ImageLoader imageLoader=ImageLoader.getInstance();

        // Define image display options



        DisplayImageOptions options = new DisplayImageOptions.Builder()

                // Cache loaded image in memory and disc

                .cacheOnDisk(true)

                .cacheInMemory(true)

                // Show Android icon while loading

                .build();

        String background= APIClient.BASE_URL +  "static/home/bg_doodle.jpg";

        imageLoader.displayImage(background, image, options);


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
