package com.example.siakad_ui.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String PREF_NAME = "SiakadPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_NIM = "nim";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_FOTO = "foto";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String id, String nim, String nama, String foto) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NIM, nim);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_FOTO, foto);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ID, sharedPreferences.getString(KEY_ID, "0"));
        user.put(KEY_NIM, sharedPreferences.getString(KEY_NIM, null));
        user.put(KEY_NAMA, sharedPreferences.getString(KEY_NAMA, null));
        user.put(KEY_FOTO, sharedPreferences.getString(KEY_FOTO, null));
        return user;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}