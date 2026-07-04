package com.example.siakad_ui.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterface {
    // Gunakan 10.0.2.2 jika menggunakan Emulator Android Studio
    // Gunakan IP Laptop jika menggunakan HP Fisik (misal: http://192.168.1.8/siakad_api/)
    private static final String BASE_URL = "http://10.0.2.2/siakad_api/";
    private static Retrofit retrofit = null;

    public static ApiService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
