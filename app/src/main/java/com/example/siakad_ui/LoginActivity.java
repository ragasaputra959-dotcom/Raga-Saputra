package com.example.siakad_ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.helper.SessionManager;
import com.example.siakad_ui.models.User;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etNim, etPassword;
    private Button btnLogin;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        etNim = findViewById(R.id.etNim);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String nim = etNim.getText().toString();
            String password = etPassword.getText().toString();

            if (nim.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "NIM dan Password harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                login(nim, password);
            }
        });
    }

    private void login(String nim, String password) {
        ApiInterface.getService().login(nim, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    sessionManager.createLoginSession(user.getId(), user.getNim(), user.getNama(), user.getFoto());
                    Toast.makeText(LoginActivity.this, "Selamat Datang, " + user.getNama(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    // Tampilkan kode error (misal 401 atau 404)
                    String pesan = "Gagal (Kode: " + response.code() + ")";
                    if (response.code() == 401) pesan = "NIM atau Password Salah!";
                    if (response.code() == 404) pesan = "File API tidak ditemukan!";
                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Tampilkan error koneksi (IP Salah atau XAMPP Mati)
                Toast.makeText(LoginActivity.this, "Koneksi Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
