package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngakakajayuk.Adapter.AnswerAdapter;
import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ngakakajayuk.Data.API.APIClient.BASE_URL;

public class GameActivity extends AppCompatActivity
        implements  AnswerAdapter.OnItemSelected{

    @BindView(R.id.rv_answer)
    RecyclerView recyclerView;

//    MediaPlayer bgm;
    TextView contentQuestion;

    List<DataAnswer> myList;
    AnswerAdapter recyclerAdapter;

    Dialog beforeGameStarted;
    Dialog dialogExit;

    String texIdRooms;

    MediaPlayer click;

    int pertanyaanNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        loadImage();

        click = MediaPlayer.create(this,R.raw.click_effect);

        beforeGameStarted = new Dialog(this);

        contentQuestion = findViewById(R.id.content_question);


        dialogExit = new Dialog(this);

//        bgm = MediaPlayer.create(this, R.raw.bgm);
//        bgm.setLooping(true);
//        bgm.start();

        getDataQuestion();
        getDataAnswer();
        displayDialog();

    }

    private void displayDialog() {
        Button shareId;
        TextView idRoom;

        Intent getIntent = getIntent();
        try {
            texIdRooms = getIntent.getStringExtra("idRoom");
        } catch (Exception e) {
            e.printStackTrace();
        }


        beforeGameStarted.setContentView(R.layout.dialog_before_game_started);

        shareId = beforeGameStarted.findViewById(R.id.btn_share_id);
        idRoom = beforeGameStarted.findViewById(R.id.tv_id_room);

        System.out.println("Ini Game Act " + texIdRooms);

        idRoom.setText(texIdRooms);

        shareId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                shareIdRoom();
            }
        });

        beforeGameStarted.show();
    }

    private void shareIdRoom() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Ayo join room, room id nya adalah " + texIdRooms);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void getDataAnswer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new AnswerAdapter(getApplicationContext(),myList, this);
        recyclerView.setAdapter(recyclerAdapter);

        RestService apiService = APIClient.getClient().create(RestService.class);
        Call<List<DataAnswer>> call = apiService.getDataAnswer();


        call.enqueue(new Callback<List<DataAnswer>>() {
            @Override
            public void onResponse(Call<List<DataAnswer>> call, Response<List<DataAnswer>> response) {
                myList = response.body();
                Log.d("TAG","Response = "+ myList);

                recyclerAdapter.setMovieList(myList);
            }

            @Override
            public void onFailure(Call<List<DataAnswer>> call, Throwable t) {
                System.out.println("gagalz");
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    private void getDataQuestion() {

        Intent getIntent = getIntent();
        String texIdRoomsz = null;
        try {
            texIdRoomsz = getIntent.getStringExtra("idRoom");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        RestService restService = APIClient.getClient().create(RestService.class);

        Call<DataRoom> call = restService.getInfoRoom(texIdRoomsz);

                call.enqueue(new Callback<DataRoom>() {
                    @Override
                    public void onResponse(Call<DataRoom> call, Response<DataRoom> response) {
                        pertanyaanNow = response.body().getPertanyaanNow() - 1;
                        System.out.println("Pertanyaan now itu " + pertanyaanNow);

                        RestService restService = APIClient.getClient().create(RestService.class);

                        Call<List<DataQuestion>> callz = restService.getDataQuestion();
                        callz.enqueue(new Callback<List<DataQuestion>>() {
                            @Override
                            public void onResponse(Call<List<DataQuestion>> call, Response<List<DataQuestion>> response) {
                                contentQuestion.setText(response.body().get(pertanyaanNow).getPertanyaan());
                                System.out.println("Pertanyaan now " + pertanyaanNow);
                            }

                            @Override
                            public void onFailure(Call<List<DataQuestion>> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<DataRoom> call, Throwable t) {

                    }
                });
    }

    /*@Override
    public void onDestroy() {
        bgm.stop();
        bgm.release();

        super.onDestroy();

    }*/

    /*@Override
    public void onPause() {
        bgm.stop();
        bgm.release();

        super.onPause();

    }*/

    /*@Override
    public void onRestart() {
        bgm.start();

        super.onRestart();

    }*/

    @Override
    public void onSelected(DataAnswer dataTestSDG) {

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

        String background= BASE_URL +  "static/home/bg_doodle.jpg";

        imageLoader.displayImage(background, image, options);


    }

}
