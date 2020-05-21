package com.example.ngakakajayuk.Data.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRoom {
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
    @SerializedName("pemain_5")
    @Expose
    private  String pemain5;
    @SerializedName("pertanyaan_now")
    @Expose
    private  int pertanyaanNow;
    @SerializedName("pertanyaan_past")
    @Expose
    private  String pertanyaanPast;
    @SerializedName("pass_room")
    @Expose
    private  String roomPassword;
    @SerializedName("pemenang_room")
    @Expose
    private  String pemenangRoom;
    @SerializedName("jml_pemain")
    @Expose
    private  int jumlahPemain;

    public int getJumlahPemain() {
        return jumlahPemain;
    }

    public void setJumlahPemain(int jumlahPemain) {
        this.jumlahPemain = jumlahPemain;
    }

    public String getPemain5() {
        return pemain5;
    }

    public void setPemain5(String pemain5) {
        this.pemain5 = pemain5;
    }

    public int getPertanyaanNow() {
        return pertanyaanNow;
    }

    public void setPertanyaanNow(int pertanyaanNow) {
        this.pertanyaanNow = pertanyaanNow;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public String getPemenangRoom() {
        return pemenangRoom;
    }

    public void setPemenangRoom(String pemenangRoom) {
        this.pemenangRoom = pemenangRoom;
    }

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



    public String getPertanyaanPast() {
        return pertanyaanPast;
    }

    public void setPertanyaanPast(String pertanyaanPast) {
        this.pertanyaanPast = pertanyaanPast;
    }
}
