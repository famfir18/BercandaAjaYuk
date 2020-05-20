package com.example.ngakakajayuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.ngakakajayuk.Data.API.APIClient;
import com.example.ngakakajayuk.Data.API.RestService;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;
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

        getIdRoom();
        System.out.println(gson.toJson(idRoom));

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etRoomCode.getText().toString().matches("")){
                    etRoomCode.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Code", Snackbar.LENGTH_SHORT)
                            .show();
                } else if (etRoomCode.getText().toString() != null && etRoomPassword.getText().toString().matches("")){
                    etRoomPassword.startAnimation(shake);
                    Snackbar.make(v, "Masukkan Room Password", Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    updateRoom();
                }
    }

    private void updateRoom() {
        codeRoom = etRoomCode.getText().toString();

        DataRoom dataRoom = new DataRoom();
        dataRoom.setPemain2(textNick.trim());

        RestService restService = APIClient.joinRoom().create(RestService.class);
        Call<ResponseBody> call = restService.JoinRoom(dataRoom, codeRoom);

        System.out.println("Kodenyaa : " + codeRoom);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
        });
    }

    private void getIdRoom() {

        RestService restService = APIClient.joinRoom().create(RestService.class);
        Call<List<DataRoom>> call = restService.GetInfoRoom();

        call.enqueue(new Callback<List<DataRoom>>(){
            @Override
            public void onResponse(Call<List<DataRoom>> call, Response<List<DataRoom>> response) {

                int length = response.body().size();
                for (int i = 0; i < length; i++){
                    idRoom = response.body().get(i).getIdRoom();
                    System.out.println("id room " + idRoom);
                }

            }

            @Override
            public void onFailure(Call<List<DataRoom>> call, Throwable t) {

                System.out.println("Gagaaal ");
                t.getCause();

            }
        });
    }
}
