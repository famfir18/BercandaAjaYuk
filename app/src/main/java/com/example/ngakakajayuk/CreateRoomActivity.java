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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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

        loadImage();

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
        int randomJudge = getRandomNumberInRange(1,jumlahPemain);

        DataRoom dataRoom = new DataRoom();
        dataRoom.setPemain1(textNick.trim());
        dataRoom.setPertanyaanNow(random);
        dataRoom.setRoomPassword(roomPassword);
        dataRoom.setJumlahPemain(jumlahPemain);
        dataRoom.setSiapaJurinya(String.valueOf(randomJudge));

        /*switch (randomJudge) {
            case 1 :
                dataRoom.setSiapaJurinya(dataRoom.getPemain1());
                break;
            case 2 :
                dataRoom.setSiapaJurinya(dataRoom.getPemain2());
                break;
        }*/

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
                int roomPasswordLength = etRoomPassword.getText().length();

                int noPlayer = 0;
                try {
                    noPlayer = Integer.parseInt(stringNoPlayer);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (noPlayer > 5 || noPlayer < 3 || stringNoPlayer.matches("")){

                    click.start();
                    etNoPlayer.startAnimation(shake);
                    Snackbar.make(v, "Game ini hanya bisa dimainkan oleh 3-5 orang saja", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (stringNoPlayer != null && roomPassword.matches("")){
                    click.start();
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Password", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (roomPasswordLength != 6){
                    click.start();
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Password harus terdiri dari 6 angka", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    click.start();
                    createRoom();
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

}
