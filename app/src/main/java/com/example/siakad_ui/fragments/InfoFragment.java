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
import com.example.siakad_ui.adapters.InformasiAdapter;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.models.Informasi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoFragment extends Fragment {

    private RecyclerView rvInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        rvInfo = view.findViewById(R.id.rvInfo);
        rvInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        
        loadInfo();
        return view;
    }

    private void loadInfo() {
        ApiInterface.getService().getInformasi().enqueue(new Callback<List<Informasi>>() {
            @Override
            public void onResponse(Call<List<Informasi>> call, Response<List<Informasi>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rvInfo.setAdapter(new InformasiAdapter(response.body(), item -> {
                        Intent intent = new Intent(getContext(), DetailInformasiActivity.class);
                        intent.putExtra("title", item.getJudul());
                        intent.putExtra("date", item.getTanggal());
                        intent.putExtra("content", item.getKonten());
                        intent.putExtra("image", item.getGambar());
                        startActivity(intent);
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Informasi>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
