package com.example.siakad_ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.siakad_ui.LoginActivity;
import com.example.siakad_ui.R;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.helper.SessionManager;
import com.example.siakad_ui.models.User;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private SessionManager sessionManager;
    private TextView tvName, tvNim, btnLogout, btnEditProfile, btnChangePassword;
    private ImageView ivProfile;
    private ActivityResultLauncher<String> mGetContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(requireContext());
        HashMap<String, String> user = sessionManager.getUserDetails();

        tvName = view.findViewById(R.id.tvProfileName);
        tvNim = view.findViewById(R.id.tvProfileNim);
        ivProfile = view.findViewById(R.id.ivProfileLarge);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);

        tvName.setText(user.get(SessionManager.KEY_NAMA));
        tvNim.setText(user.get(SessionManager.KEY_NIM));
        loadProfileImage(user.get(SessionManager.KEY_FOTO));

        // Inisialisasi ActivityResultLauncher
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                try {
                    String imageBase64 = imageUriToBase64(uri);
                    uploadProfilePicture(user.get(SessionManager.KEY_NIM), imageBase64);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Gagal memproses gambar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ketika gambar profil diklik, buka galeri
        ivProfile.setOnClickListener(v -> mGetContent.launch("image/*"));

        btnLogout.setOnClickListener(v -> {
            sessionManager.logoutUser();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        });

        btnEditProfile.setOnClickListener(v -> Toast.makeText(getContext(), "Fitur Edit Profil (Belum diimplementasi)", Toast.LENGTH_SHORT).show());
        btnChangePassword.setOnClickListener(v -> Toast.makeText(getContext(), "Fitur Ganti Password (Belum diimplementasi)", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void loadProfileImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Gambar default jika tidak ada/error
                .circleCrop() // Membuat gambar menjadi bulat
                .into(ivProfile);
    }

    private String imageUriToBase64(Uri uri) throws Exception {
        InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void uploadProfilePicture(String nim, String imageBase64) {
        ApiInterface.getService().uploadProfilePicture(nim, imageBase64).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User updatedUser = response.body();
                    // Update sesi lokal dengan foto baru
                    sessionManager.createLoginSession(updatedUser.getId(), updatedUser.getNim(), updatedUser.getNama(), updatedUser.getFoto());
                    loadProfileImage(updatedUser.getFoto()); // Muat ulang gambar dari URL baru
                    Toast.makeText(getContext(), "Foto profil berhasil diunggah!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Gagal mengunggah foto: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Error upload foto: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
