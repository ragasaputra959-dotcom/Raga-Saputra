package com.example.siakad_ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siakad_ui.DetailInformasiActivity;
import com.example.siakad_ui.R;
import com.example.siakad_ui.adapters.PengumumanAdapter;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.models.Pengumuman;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanFragment extends Fragment {

    private RecyclerView rvPengumuman;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengumuman, container, false);

        // Menghubungkan ID rvPengumuman dari XML ke Java
        rvPengumuman = view.findViewById(R.id.rvPengumuman);

        // Menggunakan requireContext() untuk menghindari warning NullPointerException
        rvPengumuman.setLayoutManager(new LinearLayoutManager(requireContext()));

        loadPengumuman();
        return view;
    }

    private void loadPengumuman() {
        ApiInterface.getService().getPengumuman().enqueue(new Callback<List<Pengumuman>>() {
            @Override
            // Menambahkan @NonNull untuk memperbaiki warning parameter override
            public void onResponse(@NonNull Call<List<Pengumuman>> call, @NonNull Response<List<Pengumuman>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rvPengumuman.setAdapter(new PengumumanAdapter(response.body(), item -> {
                        Intent intent = new Intent(requireContext(), DetailInformasiActivity.class);
                        intent.putExtra("title", item.getJudul());
                        intent.putExtra("date", item.getTanggal());
                        intent.putExtra("content", item.getKonten());
                        intent.putExtra("image", "");
                        startActivity(intent);
                    }));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Pengumuman>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}