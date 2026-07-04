package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class Krs {
    @SerializedName("kode")
    private String kode;
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("sks")
    private int sks;

    public String getKode() { return kode; }
    public String getMatkul() { return matkul; }
    public int getSks() { return sks; }
}