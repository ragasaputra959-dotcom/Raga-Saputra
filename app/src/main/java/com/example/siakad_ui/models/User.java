package com.example.siakad_ui.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String id;
    @SerializedName("nim")
    private String nim;
    @SerializedName("nama")
    private String nama;
    @SerializedName("foto")
    private String foto;

    public String getId() { return id; }
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getFoto() { return foto; }
}