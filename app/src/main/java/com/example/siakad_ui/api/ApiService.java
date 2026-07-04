package com.example.siakad_ui.api;

import com.example.siakad_ui.models.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("nim") String nim, @Field("password") String password);

    @GET("get_jadwal.php")
    Call<List<Jadwal>> getJadwal(@Query("nim") String nim);

    @GET("get_krs.php")
    Call<List<Krs>> getKrs(@Query("nim") String nim);

    @GET("get_khs.php")
    Call<List<Khs>> getKhs(@Query("nim") String nim);

    @GET("get_dosen.php")
    Call<List<Dosen>> getDosen();

    @GET("get_pengumuman.php")
    Call<List<Pengumuman>> getPengumuman();

    @GET("get_informasi.php")
    Call<List<Informasi>> getInformasi();

    @FormUrlEncoded
    @POST("upload_profile_pic.php")
    Call<User> uploadProfilePicture(@Field("nim") String nim, @Field("image") String imageBase64);
}
