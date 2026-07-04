package com.example.siakad_ui;

import com.google.gson.annotations.SerializedName;

public class Jadwal {

    @SerializedName("id")
    private String id;

    @SerializedName("nim")
    private String nim;

    @SerializedName("matkul")
    private String matkul;

    @SerializedName("dosen")
    private String dosen;

    @SerializedName("hari")
    private String hari;

    @SerializedName("jam")
    private String jam;

    @SerializedName("ruang")
    private String ruang;

    // --- INI ADALAH FUNGSI GETTER YANG DIMAKSUD ---
    public String getId() {
        return id;
    }

    public String getNim() {
        return nim;
    }

    public String getMatkul() {
        return matkul;
    }

    public String getDosen() {
        return dosen;
    }

    public String getHari() {
        return hari;
    }

    public String getJam() {
        return jam;
    }

    public String getRuang() {
        return ruang;
    }
}