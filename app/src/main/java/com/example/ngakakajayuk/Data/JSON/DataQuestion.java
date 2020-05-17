package com.example.ngakakajayuk.Data.JSON;


import com.google.gson.annotations.SerializedName;

public class DataQuestion {
    @SerializedName("id_name")
    Integer id;
    @SerializedName("pertanyaan")
    String pertanyaan;
    @SerializedName("jml_jwbn")
    Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
