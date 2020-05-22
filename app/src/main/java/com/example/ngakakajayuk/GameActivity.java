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
import android.widget.TextView;

import com.example.ngakakajayuk.Adapter.AnswerAdapter;
import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

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
        if (getIntent.getExtras() != null){
            texIdRooms = getIntent.getStringExtra("idRoom");
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

        Gson gson = new Gson();

        RestService restService = APIClient.getClient().create(RestService.class);
        Call<List<DataQuestion>> call = restService.getDataQuestion();

        call.enqueue(new Callback<List<DataQuestion>>() {
            @Override
            public void onResponse(Call<List<DataQuestion>> call, Response<List<DataQuestion>> response) {

                Random random = new Random();
                int length = response.body().size();

                for (int i = 0; i < length; i++){

                    int randomInteger = random.nextInt(length);

                    if (response.isSuccessful()){
                        contentQuestion.setText(response.body().get(randomInteger).getPertanyaan());

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
}
