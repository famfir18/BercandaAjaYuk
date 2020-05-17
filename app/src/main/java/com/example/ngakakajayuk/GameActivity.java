package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ngakakajayuk.Adapter.AnswerAdapter;
import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity
        implements  AnswerAdapter.OnItemSelected{

    @BindView(R.id.rv_answer)
    RecyclerView recyclerView;

    MediaPlayer bgm;
    TextView contentQuestion;

    List<DataAnswer> myList;
    AnswerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);


        contentQuestion = findViewById(R.id.content_question);

        bgm = MediaPlayer.create(this, R.raw.bgm);
        bgm.setLooping(true);
        bgm.start();

        getDataQuestion();

        getDataAnswer();

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

    @Override
    public void onDestroy() {
        bgm.stop();
        bgm.release();

        super.onDestroy();

    }

    @Override
    public void onPause() {
        bgm.stop();
        bgm.release();

        super.onPause();

    }

    @Override
    public void onRestart() {
        bgm.start();

        super.onRestart();

    }

    @Override
    public void onSelected(DataAnswer dataTestSDG) {

    }
}
