package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.nick)
    TextView nickName;
    @BindView(R.id.btn_create)
    ImageButton btnCreate;
    @BindView(R.id.btn_join)
    ImageButton btnJoin;
    @BindView(R.id.btn_settings)
    ImageButton btnSetting;
    @BindView(R.id.btn_exit)
    ImageButton btnExit;

    Handler handler = new Handler();
    final static int DELAY = 500;

    Dialog dialogExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        loadImage();

        dialogExit = new Dialog(this);

        final Animation animScaleTitle = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        MediaPlayer click = MediaPlayer.create(this,R.raw.click_effect);

        Intent getIntent = getIntent();
        String textNick = getIntent.getStringExtra("nickName");

        nickName.setText("Welcome, " + textNick);

        handler.postDelayed(() -> {
            nickName.setVisibility(View.VISIBLE);
            nickName.startAnimation(animScaleTitle);
        }, DELAY);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, CreateRoomActivity.class);
                click.start();
                i.putExtra("nickName", textNick);
                startActivity(i);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, JoinRoomActivity.class);
                click.start();
                i.putExtra("nickName", textNick);
                startActivity(i);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, CreateRoomActivity.class);
                click.start();
//                startActivity(i);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();

                Button yes;
                Button no;

                dialogExit.setContentView(R.layout.dialog_exit);

                yes = dialogExit.findViewById(R.id.btnYes);
                no = dialogExit.findViewById(R.id.btnNo);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.start();
                        finish();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.start();
                        dialogExit.dismiss();
                    }
                });

                Objects.requireNonNull(dialogExit.getWindow()).setBackgroundDrawableResource(R.color.transparent);
                dialogExit.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        Button yes;
        Button no;

        dialogExit.setContentView(R.layout.dialog_exit);

        yes = dialogExit.findViewById(R.id.btnYes);
        no = dialogExit.findViewById(R.id.btnNo);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogExit.dismiss();
            }
        });

        Objects.requireNonNull(dialogExit.getWindow()).setBackgroundDrawableResource(R.color.transparent);
        dialogExit.show();

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

}
