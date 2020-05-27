package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        ButterKnife.bind(this);

        click = MediaPlayer.create(this, R.raw.click_effect);

        loadImage();

        Intent getIntent = getIntent();

        textNick = getIntent.getStringExtra("nickName");

        dataRoom = new DataRoom();


        shake = AnimationUtils.loadAnimation(this, R.anim.anim_shake);

        Gson gson = new Gson();


        //OnClick Button Join
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roomPasswordLength = etRoomPassword.getText().length();

                if (etRoomCode.getText().toString().matches("")) {
                    click.start();
                    etRoomCode.startAnimation(shake);
                    Snackbar.make(v, "Masukkan room code", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (etRoomCode.getText().toString() != null && etRoomPassword.getText().toString().matches("")) {
                    click.start();
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Masukkan room password", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (roomPasswordLength != 6){
                    click.start();
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Room password harus terdiri dari 6 angka", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    click.start();
                    codeRoom = etRoomCode.getText().toString();
                    passwordRoom = etRoomPassword.getText().toString();

                    //menentukan pemain merupakan pemain ke berapa
                    DataRoom dataRoom = new DataRoom();

                    idRoom = etRoomCode.getText().toString();
                    gettingIDRoom();
                    System.out.println("Setaaaan " + idRoom);

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
        Call<DataRoom> callz = restService.getInfoRoom(idRoom);

        callz.enqueue(new Callback<DataRoom>(){
            @Override
            public void onResponse(Call<DataRoom> calls, Response<DataRoom> response) {

                if (response.isSuccessful()){
                    pemain2 = response.body().getPemain2();
                    pemain3 = response.body().getPemain3();
                    pemain4 = response.body().getPemain4();
                    pemain5 = response.body().getPemain5();
                    int pertanyaanNow = response.body().getPertanyaanNow();
                    int jumlahPemain = response.body().getJumlahPemain();
                    String passwordRuangan = response.body().getRoomPassword();

                    if (!passwordRoom.equals(passwordRuangan)){
                        Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_LONG).show();
                    } else {

//                    idRoom = etRoomCode.getText().toString();
                        System.out.println("id room " + idRoom);

                        if (jumlahPemain == 5){
                            if (pemain2 == null || pemain2.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(5);
                                dataRoom.setPemain2(textNick.trim());
                            } else if (!pemain2.equals("") && pemain3 == null || pemain3.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(5);
                                dataRoom.setPemain3(textNick.trim());
                            } else if (!pemain3.equals("") && pemain4 == null || pemain4.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(5);
                                dataRoom.setPemain4(textNick.trim());
                            } else if (!pemain4.equals("") && pemain5 == null || pemain5.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(5);
                                dataRoom.setPemain5(textNick.trim());
                            } else {
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(4);
                                Toast.makeText(getApplicationContext(), "Room Sudah Penuh" , Toast.LENGTH_LONG).show();
                            }
                        } else if (jumlahPemain == 4){
                            if (pemain2 == null || pemain2.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(4);
                                dataRoom.setPemain2(textNick.trim());
                            } else if (!pemain2.equals("") && pemain3 == null || pemain3.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(4);
                                dataRoom.setPemain3(textNick.trim());
                            } else if (!pemain3.equals("") && pemain4 == null || pemain4.equals("")){
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(4);
                                dataRoom.setPemain4(textNick.trim());
                            } else {
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(4);
                                Toast.makeText(getApplicationContext(), "Room Sudah Penuh" , Toast.LENGTH_LONG).show();
                            }
                        } else if (jumlahPemain == 3) {
                            if (pemain2 == null || pemain2.equals("")) {
                                dataRoom.setPertanyaanNow(pertanyaanNow);
                                dataRoom.setJumlahPemain(3);
                                dataRoom.setPemain2(textNick.trim());
                            } else if (!pemain2.equals("") && pemain3 == null || pemain3.equals("")) {
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


                            }

                            @Override
                            public void onFailure(Call<DataRoom> call, Throwable t) {

                            }
                        });
                        Intent i = new Intent(JoinRoomActivity.this, GameActivity.class);
                        i.putExtra("roomCode", codeRoom);
                        startActivity(i);
                    }

                } else {
                    etRoomCode.startAnimation(shake);
                    Toast.makeText(getApplicationContext(), "Room code tidak ditemukan/ salah, mohon teliti huruf kapital dalam pengisian room code", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<DataRoom> call, Throwable t) {

                System.out.println("Gagaaal ");
                t.getCause();

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

}
