package com.example.siakad_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.R;
import com.example.siakad_ui.models.Jadwal;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private List<Jadwal> list;

    public JadwalAdapter(List<Jadwal> list) { this.list = list; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jadwal j = list.get(position);
        holder.tvMatkul.setText(j.getMatkul());
        holder.tvDosen.setText(j.getDosen());
        holder.tvWaktu.setText(j.getHari() + ", " + j.getJam());
        holder.tvRuang.setText(j.getRuang());
    }

    @Override
    public int getItemCount() { return list != null ? list.size() : 0; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatkul, tvDosen, tvWaktu, tvRuang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvDosen = itemView.findViewById(R.id.tvDosen);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
            tvRuang = itemView.findViewById(R.id.tvRuang);
        }
    }
}