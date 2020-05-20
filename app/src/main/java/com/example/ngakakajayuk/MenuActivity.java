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
import android.widget.TextView;

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
    final static int DELAY = 1000;

    Dialog dialogExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        dialogExit = new Dialog(this);

        final Animation animScaleTitle = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

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

        dialogExit.show();

    }
}
