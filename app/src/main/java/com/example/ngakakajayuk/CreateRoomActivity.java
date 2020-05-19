package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateRoomActivity extends AppCompatActivity {

    @BindView(R.id.et_no_player)
    EditText etNoPlayer;
    @BindView(R.id.et_room_code)
    EditText etRoomCode;
    @BindView(R.id.et_room_password)
    EditText etRoomPassword;
    @BindView(R.id.btn_create)
    Button btnCreate;

    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        ButterKnife.bind(this);

        shake = AnimationUtils.loadAnimation(this, R.anim.anim_shake);

        initEvent();

    }

    private void initEvent() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringNoPlayer = etNoPlayer.getText().toString();
                int noPlayer = Integer.parseInt(stringNoPlayer);
                if (noPlayer > 5 || noPlayer < 3){

                    etNoPlayer.startAnimation(shake);
                    Snackbar.make(v, "Game ini hanya bisa dimainkan oleh 3-5 orang saja", Snackbar.LENGTH_SHORT)
                            .show();
                }

                Intent i = new Intent(CreateRoomActivity.this, GameActivity.class);
                startActivity(i);
            }
        });
    }
}
