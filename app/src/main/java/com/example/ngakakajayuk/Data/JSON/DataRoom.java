package com.example.ngakakajayuk.Data.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRoom {
    @SerializedName("id_room")
    @Expose
    private String idRoom;
    @SerializedName("pemain_1")
    @Expose
    private String pemain1;
    @SerializedName("pemain_2")
    @Expose
    private String pemain2;
    @SerializedName("pemain_3")
    @Expose
    private String pemain3;
    @SerializedName("pemain_4")
    @Expose
    private String pemain4;
    @SerializedName("pemain_5")
    @Expose
    private String pemain5;
    @SerializedName("pertanyaan_now")
    @Expose
    private int pertanyaanNow;
    @SerializedName("pertanyaan_past")
    @Expose
    private String pertanyaanPast;
    @SerializedName("pass_room")
    @Expose
    private String roomPassword;
    @SerializedName("pemenang_room")
    @Expose
    private String pemenangRoom;
    @SerializedName("jml_pemain")
    @Expose
    private int jumlahPemain;
    @SerializedName("jwbn_pemain_1")
    @Expose
    private String jwbnPemain1;
    @SerializedName("jwbn_pemain_2")
    @Expose
    private String jwbnPemain2;
    @SerializedName("jwbn_pemain_3")
    @Expose
    private String jwbnPemain3;
    @SerializedName("jwbn_pemain_4")
    @Expose
    private String jwbnPemain4;
    @SerializedName("jwbn_pemain_5")
    @Expose
    private String jwbnPemain5;
    @SerializedName("nilai_p1")
    @Expose
    private String nilaiPemain1;
    @SerializedName("nilai_p2")
    @Expose
    private String nilaiPemain2;
    @SerializedName("nilai_p3")
    @Expose
    private String nilaiPemain3;
    @SerializedName("nilai_p4")
    @Expose
    private String nilaiPemain4;
    @SerializedName("nilai_p5")
    @Expose
    private String nilaiPemain5;
    @SerializedName("juri_judge")
    @Expose
    private String siapaJurinya;
    @SerializedName("plhn_juri")
    @Expose
    private String pilihanJuri;
    @SerializedName("start_game")
    @Expose
    private Boolean startGame;
    @SerializedName("check_jwbn")
    @Expose
    private Boolean jawabanPalingLucu;
    @SerializedName("end_game")
    @Expose
    private Boolean endGame;


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

    public String getJwbnPemain1() {
        return jwbnPemain1;
    }

    public void setJwbnPemain1(String jwbnPemain1) {
        this.jwbnPemain1 = jwbnPemain1;
    }

    public String getJwbnPemain2() {
        return jwbnPemain2;
    }

    public void setJwbnPemain2(String jwbnPemain2) {
        this.jwbnPemain2 = jwbnPemain2;
    }

    public String getJwbnPemain3() {
        return jwbnPemain3;
    }

    public void setJwbnPemain3(String jwbnPemain3) {
        this.jwbnPemain3 = jwbnPemain3;
    }

    public String getJwbnPemain4() {
        return jwbnPemain4;
    }

    public void setJwbnPemain4(String jwbnPemain4) {
        this.jwbnPemain4 = jwbnPemain4;
    }

    public String getJwbnPemain5() {
        return jwbnPemain5;
    }

    public void setJwbnPemain5(String jwbnPemain5) {
        this.jwbnPemain5 = jwbnPemain5;
    }

    public String getNilaiPemain1() {
        return nilaiPemain1;
    }

    public void setNilaiPemain1(String nilaiPemain1) {
        this.nilaiPemain1 = nilaiPemain1;
    }

    public String getNilaiPemain2() {
        return nilaiPemain2;
    }

    public void setNilaiPemain2(String nilaiPemain2) {
        this.nilaiPemain2 = nilaiPemain2;
    }

    public String getNilaiPemain3() {
        return nilaiPemain3;
    }

    public void setNilaiPemain3(String nilaiPemain3) {
        this.nilaiPemain3 = nilaiPemain3;
    }

    public String getNilaiPemain4() {
        return nilaiPemain4;
    }

    public void setNilaiPemain4(String nilaiPemain4) {
        this.nilaiPemain4 = nilaiPemain4;
    }

    public String getNilaiPemain5() {
        return nilaiPemain5;
    }

    public void setNilaiPemain5(String nilaiPemain5) {
        this.nilaiPemain5 = nilaiPemain5;
    }

    public String getSiapaJurinya() {
        return siapaJurinya;
    }

    public void setSiapaJurinya(String siapaJurinya) {
        this.siapaJurinya = siapaJurinya;
    }

    public String getPilihanJuri() {
        return pilihanJuri;
    }

    public void setPilihanJuri(String pilihanJuri) {
        this.pilihanJuri = pilihanJuri;
    }

    public Boolean getStartGame() {
        return startGame;
    }

    public void setStartGame(Boolean startGame) {
        this.startGame = startGame;
    }

    public Boolean getJawabanPalingLucu() {
        return jawabanPalingLucu;
    }

    public void setJawabanPalingLucu(Boolean jawabanPalingLucu) {
        this.jawabanPalingLucu = jawabanPalingLucu;
    }

    public Boolean getEndGame() {
        return endGame;
    }

    public void setEndGame(Boolean endGame) {
        this.endGame = endGame;
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
