package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class Khs {
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("sks")
    private int sks;
    @SerializedName("nilai")
    private String nilai;

    public String getMatkul() { return matkul; }
    public int getSks() { return sks; }
    public String getNilai() { return nilai; }
}