package com.example.ngakakajayuk.Data.API;

import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.Data.JSON.DataQuestion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestService {

    @GET("pertanyaan")
    Call<List<DataQuestion>> getDataQuestion();

    @GET("jawaban")
    Call<List<DataAnswer>> getDataAnswer();

}
