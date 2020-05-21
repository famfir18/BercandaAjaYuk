package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {

    @BindView(R.id.et_no_player)
    EditText etNoPlayer;
    @BindView(R.id.et_room_password)
    EditText etRoomPassword;
    @BindView(R.id.btn_create)
    Button btnCreate;

    Animation shake;
    String stringNoPlayer;

    DataRoom dataRoom;

    String textNick;
    String idRoom;
    String roomPassword;
    String stringJumlahPemain;

    int jumlahPertanyan;
    int jumlahPemain;

    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        ButterKnife.bind(this);

        Intent getIntent = getIntent();
        textNick = getIntent.getStringExtra("nickName");

        dataRoom = new DataRoom();

        click = MediaPlayer.create(this, R.raw.click_effect);

        shake = AnimationUtils.loadAnimation(this, R.anim.anim_shake);
        System.out.println("Range : " + getRandomNumberInRange(1, 46));

        initEvent();
        getJumlahPertanyaan();

    }

    private void getJumlahPertanyaan() {
        RestService restService = APIClient.getClient().create(RestService.class);
        Call<List<DataQuestion>> call = restService.getDataQuestion();

        call.enqueue(new Callback<List<DataQuestion>>() {
            @Override
            public void onResponse(Call<List<DataQuestion>> call, Response<List<DataQuestion>> response) {

                int length = response.body().size();

                for (int i = 0; i < length; i++){

                    if (response.isSuccessful()){

                        jumlahPertanyan = response.body().size();

                        System.out.println("Hasil Datanya Adalah : ");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DataQuestion>> call, Throwable t) {

                System.out.println("Gagaaal ");
                t.getCause();

            }
        });
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void createRoom() {

        stringJumlahPemain = etNoPlayer.getText().toString();
        jumlahPemain = Integer.parseInt(stringJumlahPemain);
        roomPassword = etRoomPassword.getText().toString();


        int random = getRandomNumberInRange(1,jumlahPertanyan);

        DataRoom dataRoom = new DataRoom();
        dataRoom.setPemain1(textNick.trim());
        dataRoom.setPertanyaanNow(random);
        dataRoom.setRoomPassword(roomPassword);
        dataRoom.setJumlahPemain(jumlahPemain);

        RestService restService = APIClient.createNewRoom().create(RestService.class);
        Call<DataRoom> call = restService.createNewRoom(dataRoom);

        call.enqueue(new Callback<DataRoom>() {
            @Override
            public void onResponse(Call<DataRoom> call, Response<DataRoom> response) {

//                JsonObject post = new JsonObject().get(response.body().toString()).getAsJsonObject();
                if(response.isSuccessful()){
                    response.body(); // have your all data
                    idRoom = response.body().getIdRoom();

                    Intent i = new Intent(CreateRoomActivity.this, GameActivity.class);
                    i.putExtra("idRoom", idRoom);
                    System.out.println("Ini Create Act " + idRoom);
                    startActivity(i);

                    System.out.println("apaansiii " + idRoom);

                } else   Toast.makeText(getApplicationContext(),response.errorBody().toString(), Toast.LENGTH_SHORT).show();

                Log.i("Respone Body %s", String.valueOf(response.code()));


            }

            @Override
            public void onFailure(Call<DataRoom> call, Throwable t) {

            }
        });
    }

    private void initEvent() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringNoPlayer = etNoPlayer.getText().toString();
                String roomPassword = etRoomPassword.getText().toString();

                int noPlayer = 0;
                try {
                    noPlayer = Integer.parseInt(stringNoPlayer);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (noPlayer > 5 || noPlayer < 3 || stringNoPlayer.matches("")){

                    etNoPlayer.startAnimation(shake);
                    Snackbar.make(v, "Game ini hanya bisa dimainkan oleh 3-5 orang saja", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (stringNoPlayer != null && roomPassword.matches("")){
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Password", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    click.start();
                    createRoom();

                }
            }
        });
    }
}
