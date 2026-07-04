package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class Informasi {
    @SerializedName("id")
    private int id;
    @SerializedName("judul")
    private String judul;
    @SerializedName("konten")
    private String konten;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("gambar")
    private String gambar;

    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getKonten() { return konten; }
    public String getTanggal() { return tanggal; }
    public String getGambar() { return gambar; }
}