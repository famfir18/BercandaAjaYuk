package com.example.ngakakajayuk.Data.API;

import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.Data.JSON.DataRoom;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestService {

    @GET("pertanyaan")
    Call<List<DataQuestion>> getDataQuestion();

    @GET("jawaban")
    Call<List<DataAnswer>> getDataAnswer();

    @POST("ruang/")
    Call<DataRoom> createNewRoom(@Body DataRoom dataRoom);

    @Headers({"Content-Type: application/json"})
    @PUT("ruang/{id_room}/")
    Call<DataRoom> JoinRoom(@Body DataRoom dataRoom,
                                @Path("id_room") String idRoom);

    @GET("ruang/{id_room}/")
    Call<DataRoom> getInfoRoom(@Path("id_room") String idRoom);

    @GET("ruang/{id_room}/")
    Observable<DataRoom> getObservableRoom(@Path("id_room") String idRoom);

}
