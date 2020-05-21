package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinRoomActivity extends AppCompatActivity {

    @BindView(R.id.et_room_code)
    EditText etRoomCode;
    @BindView(R.id.et_room_password)
    EditText etRoomPassword;
    @BindView(R.id.btn_join)
    Button btnJoin;

    DataRoom dataRoom;

    Animation shake;
    String textNick;

    String idRoom;
    String passwordRoom;

    String pemain2;
    String pemain3;
    String pemain4;
    String pemain5;

    String codeRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        ButterKnife.bind(this);

        Intent getIntent = getIntent();

        textNick = getIntent.getStringExtra("nickName");

        dataRoom = new DataRoom();


        shake = AnimationUtils.loadAnimation(this, R.anim.anim_shake);

        Gson gson = new Gson();


        //OnClick Button Join
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRoomCode.getText().toString().matches("")) {
                    etRoomCode.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Code", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (etRoomCode.getText().toString() != null && etRoomPassword.getText().toString().matches("")) {
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Password", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    codeRoom = etRoomCode.getText().toString();
                    passwordRoom = etRoomPassword.getText().toString();

                    //menentukan pemain merupakan pemain ke berapa
                    DataRoom dataRoom = new DataRoom();

                    idRoom = etRoomCode.getText().toString();
                    gettingIDRoom();
                    System.out.println("Setaaaan " + idRoom);

                 /*   if (jumlahPemain == 0){
                        if (pemain2 == null){
                            dataRoom.setPemain2(textNick.trim());
                        } else if (!pemain2.matches("")){
                            dataRoom.setPemain3(textNick.trim());
                        } else if (!pemain3.matches("")){
                            dataRoom.setPemain4(textNick.trim());
                        }
                    }*/

                  /*  if (!etRoomCode.getText().toString().equals(codeRoom)) {
                        Snackbar.make(v, "Room code tidak ditemukan/ salah, mohon teliti huruf kapital dalam pengisian room code", Snackbar.LENGTH_SHORT)
                                .show();
                    } else {*/

                    }
//                }
            }
        });
    }

    private void gettingIDRoom() {

        RestService restService = APIClient.joinRoom().create(RestService.class);
        Call<DataRoom> callz = restService.GetInfoRoom(idRoom);

        callz.enqueue(new Callback<DataRoom>(){
            @Override
            public void onResponse(Call<DataRoom> calls, Response<DataRoom> response) {

                pemain2 = response.body().getPemain2();
                pemain3 = response.body().getPemain3();
                pemain4 = response.body().getPemain4();
                pemain5 = response.body().getPemain5();
                int pertanyaanNow = response.body().getPertanyaanNow();
                int jumlahPemain = response.body().getJumlahPemain();

//                    idRoom = etRoomCode.getText().toString();
                    System.out.println("id room " + idRoom);

                if (jumlahPemain == 5){
                    if (pemain2 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(5);
                        dataRoom.setPemain2(textNick.trim());
                    } else if (!pemain2.equals("") && pemain3 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(5);
                        dataRoom.setPemain3(textNick.trim());
                    } else if (!pemain3.equals("") && pemain4 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(5);
                        dataRoom.setPemain4(textNick.trim());
                    } else if (!pemain4.equals("") && pemain5 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(5);
                        dataRoom.setPemain5(textNick.trim());
                    } else {
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(4);
                        Toast.makeText(getApplicationContext(), "Room Sudah Penuh" , Toast.LENGTH_LONG).show();
                    }
                } else if (jumlahPemain == 4){
                    if (pemain2 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(4);
                        dataRoom.setPemain2(textNick.trim());
                    } else if (!pemain2.equals("") && pemain3 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(4);
                        dataRoom.setPemain3(textNick.trim());
                    } else if (!pemain3.equals("") && pemain4 == null){
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(4);
                        dataRoom.setPemain4(textNick.trim());
                    } else {
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(4);
                        Toast.makeText(getApplicationContext(), "Room Sudah Penuh" , Toast.LENGTH_LONG).show();
                    }
                } else if (jumlahPemain == 3) {
                    if (pemain2 == null) {
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(3);
                        dataRoom.setPemain2(textNick.trim());
                    } else if (!pemain2.equals("") && pemain3 == null) {
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(3);
                        dataRoom.setPemain3(textNick.trim());
                    } else {
                        dataRoom.setPertanyaanNow(pertanyaanNow);
                        dataRoom.setJumlahPemain(3);
                        Toast.makeText(getApplicationContext(), "Room Sudah Penuh", Toast.LENGTH_LONG).show();
                    }
                }

//                RestService restService = APIClient.joinRoom().create(RestService.class);
                Call<DataRoom> call = restService.JoinRoom(dataRoom, codeRoom);

                System.out.println("Kodenyaa : " + codeRoom);


                call.enqueue(new Callback<DataRoom>() {
                    @Override
                    public void onResponse(Call<DataRoom> call, Response<DataRoom> response) {

                               /* pemain2 = response.body().getPemain2();
                                System.out.println("Pemain 2 : " + pemain2);
                                pemain3 = response.body().getPemain3();
                                pemain4 = response.body().getPemain4();
                                pemain5 = response.body().getPemain5();*/

                              /*  if (jumlahPemain == 0){
                                    if (pemain2 == null){
                                        dataRoom.setPemain2(textNick.trim());
                                    } else if (!pemain2.matches("")){
                                        dataRoom.setPemain3(textNick.trim());
                                    } else if (!pemain3.matches("")){
                                        dataRoom.setPemain4(textNick.trim());
                                    }
                                }*/
                    }

                    @Override
                    public void onFailure(Call<DataRoom> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<DataRoom> call, Throwable t) {

                System.out.println("Gagaaal ");
                t.getCause();

            }
        });
    }
}
