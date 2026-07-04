package com.example.siakad_ui.models;

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

    // Constructor Kosong (untuk Retrofit)
    public Jadwal() {}

    // Constructor dengan Parameter (untuk Testing/Manual)
    public Jadwal(String matkul, String dosen, String hari, String jam, String ruang) {
        this.matkul = matkul;
        this.dosen = dosen;
        this.hari = hari;
        this.jam = jam;
        this.ruang = ruang;
    }

    // Getter dan Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getMatkul() { return matkul; }
    public void setMatkul(String matkul) { this.matkul = matkul; }
    public String getDosen() { return dosen; }
    public void setDosen(String dosen) { this.dosen = dosen; }
    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }
    public String getJam() { return jam; }
    public void setJam(String jam) { this.jam = jam; }
    public String getRuang() { return ruang; }
    public void setRuang(String ruang) { this.ruang = ruang; }
}
