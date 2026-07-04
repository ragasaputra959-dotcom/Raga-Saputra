package com.example.siakad_ui.fragments;

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
import com.example.siakad_ui.R;
import com.example.siakad_ui.adapters.KrsAdapter;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.helper.SessionManager;
import com.example.siakad_ui.models.Krs;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KrsFragment extends Fragment {

    private RecyclerView rvKrs;
    private SessionManager sessionManager;
    private LinearProgressIndicator krsProgress;
    private ExtendedFloatingActionButton fabPrint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment_krs.xml
        View view = inflater.inflate(R.layout.fragment_krs, container, false);
        
        // Inisialisasi View sesuai dengan layout terbaru
        rvKrs = view.findViewById(R.id.rvKrs);
        krsProgress = view.findViewById(R.id.krsProgress);
        fabPrint = view.findViewById(R.id.fabPrintKrs);
        
        // Pengaturan RecyclerView
        rvKrs.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvKrs.setHasFixedSize(true);
        rvKrs.setNestedScrollingEnabled(false); // Matikan scroll agar tidak bentrok dengan NestedScrollView
        
        sessionManager = new SessionManager(requireContext());
        
        // Tombol Cetak KRS
        if (fabPrint != null) {
            fabPrint.setOnClickListener(v -> 
                Toast.makeText(requireContext(), "Menyiapkan cetak KRS (PDF)...", Toast.LENGTH_SHORT).show()
            );
        }
        
        loadKrs();
        return view;
    }

    private void loadKrs() {
        String nim = sessionManager.getUserDetails().get(SessionManager.KEY_NIM);
        
        // Tampilkan loading bar
        if (krsProgress != null) {
            krsProgress.setVisibility(View.VISIBLE);
        }

        ApiInterface.getService().getKrs(nim).enqueue(new Callback<List<Krs>>() {
            @Override
            public void onResponse(@NonNull Call<List<Krs>> call, @NonNull Response<List<Krs>> response) {
                // Sembunyikan loading bar
                if (krsProgress != null) krsProgress.setVisibility(View.INVISIBLE);
                
                if (response.isSuccessful() && response.body() != null) {
                    List<Krs> list = response.body();
                    rvKrs.setAdapter(new KrsAdapter(list));
                    
                    if (list.isEmpty()) {
                        Toast.makeText(getContext(), "Belum ada mata kuliah terdaftar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal memuat KRS. Kode: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Krs>> call, @NonNull Throwable t) {
                if (krsProgress != null) krsProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Kesalahan Jaringan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
