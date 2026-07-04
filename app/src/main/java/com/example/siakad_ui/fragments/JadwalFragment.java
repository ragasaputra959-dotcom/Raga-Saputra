package com.example.siakad_ui.fragments;import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.siakad_ui.R;
import com.example.siakad_ui.adapters.JadwalAdapter;
import com.example.siakad_ui.models.Jadwal;

import java.util.ArrayList;
import java.util.List;

public class JadwalFragment extends Fragment {
    private RecyclerView rvJadwal;
    private JadwalAdapter adapter;
    private List<Jadwal> listJadwal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

        // Menghubungkan ID dari fragment_jadwal.xml
        rvJadwal = view.findViewById(R.id.rvJadwal);
        rvJadwal.setLayoutManager(new LinearLayoutManager(getContext()));

        listJadwal = new ArrayList<>();
        listJadwal.add(new Jadwal("Pemrograman Mobile", "Dr. Raga", "Senin", "08:00", "Lab 1"));
        listJadwal.add(new Jadwal("Basis Data", "Siti M.Kom", "Selasa", "10:00", "R. 302"));

        adapter = new JadwalAdapter(listJadwal);
        rvJadwal.setAdapter(adapter);

        return view;
    }
}