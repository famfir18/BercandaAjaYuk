package com.example.ngakakajayuk.Data.JSON;

import com.google.gson.annotations.SerializedName;

public class DataAnswer {
    @SerializedName("id_name")
    Integer id;
    @SerializedName("jawaban")
    String jawaban;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
