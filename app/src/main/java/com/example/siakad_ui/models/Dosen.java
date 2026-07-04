package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class Dosen {
    @SerializedName("nama")
    private String nama;
    @SerializedName("nidn")
    private String nidn;
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("foto")
    private String foto;

    public String getNama() { return nama; }
    public String getNidn() { return nidn; }
    public String getMatkul() { return matkul; }
    public String getFoto() { return foto; }
}