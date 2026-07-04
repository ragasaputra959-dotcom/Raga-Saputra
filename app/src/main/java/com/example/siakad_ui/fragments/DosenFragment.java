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
import com.example.siakad_ui.adapters.DosenAdapter;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.models.Dosen;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenFragment extends Fragment {

    private RecyclerView rvDosen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dosen, container, false);
        rvDosen = view.findViewById(R.id.rvDosen);
        rvDosen.setLayoutManager(new LinearLayoutManager(getContext()));
        
        loadDosen();
        return view;
    }

    private void loadDosen() {
        ApiInterface.getService().getDosen().enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rvDosen.setAdapter(new DosenAdapter(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
