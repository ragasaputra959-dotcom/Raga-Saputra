package com.example.siakad_ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.R;
import com.example.siakad_ui.adapters.KhsAdapter;
import com.example.siakad_ui.api.ApiInterface;
import com.example.siakad_ui.helper.SessionManager;
import com.example.siakad_ui.models.Khs;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhsFragment extends Fragment {

    private RecyclerView rvKhs;
    private TextView tvIp, tvIpk;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khs, container, false);
        rvKhs = view.findViewById(R.id.btnChangePassword);
        tvIp = view.findViewById(R.id.tvIp);
        tvIpk = view.findViewById(R.id.tvIpk);
        rvKhs.setLayoutManager(new LinearLayoutManager(getContext()));
        sessionManager = new SessionManager(getContext());
        
        loadKhs();
        return view;
    }

    private void loadKhs() {
        String nim = sessionManager.getUserDetails().get(SessionManager.KEY_NIM);
        ApiInterface.getService().getKhs(nim).enqueue(new Callback<List<Khs>>() {
            @Override
            public void onResponse(Call<List<Khs>> call, Response<List<Khs>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Khs> list = response.body();
                    rvKhs.setAdapter(new KhsAdapter(list));
                    calculateIp(list);
                }
            }

            @Override
            public void onFailure(Call<List<Khs>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateIp(List<Khs> list) {
        double totalNilai = 0;
        int totalSks = 0;
        for (Khs k : list) {
            double bobot = getBobot(k.getNilai());
            totalNilai += (bobot * k.getSks());
            totalSks += k.getSks();
        }
        if (totalSks > 0) {
            double ip = totalNilai / totalSks;
            tvIp.setText(String.format("%.2f", ip));
            tvIpk.setText(String.format("%.2f", ip)); // Demo IPK = IP
        }
    }

    private double getBobot(String nilai) {
        switch (nilai) {
            case "A": return 4.0;
            case "B+": return 3.5;
            case "B": return 3.0;
            case "C+": return 2.5;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }
}
