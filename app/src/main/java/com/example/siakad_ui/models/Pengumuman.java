package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class Pengumuman {
    @SerializedName("id")
    private int id;
    @SerializedName("judul")
    private String judul;
    @SerializedName("konten")
    private String konten;
    @SerializedName("tanggal")
    private String tanggal;

    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getKonten() { return konten; }
    public String getTanggal() { return tanggal; }
}