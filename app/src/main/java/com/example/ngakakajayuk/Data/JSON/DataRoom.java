package com.example.ngakakajayuk.Data.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCreateRoom {
    @SerializedName("id_room")
    @Expose
    private  String idRoom;
    @SerializedName("pemain_1")
    @Expose
    private  String pemain1;
    @SerializedName("pemain_2")
    @Expose
    private  String pemain2;
    @SerializedName("pemain_3")
    @Expose
    private  String pemain3;
    @SerializedName("pemain_4")
    @Expose
    private  String pemain4;
    @SerializedName("pertanyaan_now")
    @Expose
    private  String pertanyaanNow;
    @SerializedName("pertanyaan_past")
    @Expose
    private  String pertanyaanPast;

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getPemain1() {
        return pemain1;
    }

    public void setPemain1(String pemain1) {
        this.pemain1 = pemain1;
    }

    public String getPemain2() {
        return pemain2;
    }

    public void setPemain2(String pemain2) {
        this.pemain2 = pemain2;
    }

    public String getPemain3() {
        return pemain3;
    }

    public void setPemain3(String pemain3) {
        this.pemain3 = pemain3;
    }

    public String getPemain4() {
        return pemain4;
    }

    public void setPemain4(String pemain4) {
        this.pemain4 = pemain4;
    }

    public String getPertanyaanNow() {
        return pertanyaanNow;
    }

    public void setPertanyaanNow(String pertanyaanNow) {
        this.pertanyaanNow = pertanyaanNow;
    }

    public String getPertanyaanPast() {
        return pertanyaanPast;
    }

    public void setPertanyaanPast(String pertanyaanPast) {
        this.pertanyaanPast = pertanyaanPast;
    }
}
