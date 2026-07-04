package com.example.siakad_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.models.Jadwal;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private List<Jadwal> list;

    public JadwalAdapter(List<Jadwal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jadwal item = list.get(position);
        holder.tvMatkul.setText(item.getMatkul());
        holder.tvDosen.setText(item.getDosen());
        holder.tvWaktu.setText(item.getHari() + ", " + item.getJam());
        holder.tvRuang.setText(item.getRuang());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatkul, tvDosen, tvWaktu, tvRuang;
        ViewHolder(View itemView) {
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvMatkul);
            tvDosen = itemView.findViewById(R.id.tvDosen);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
            tvRuang = itemView.findViewById(R.id.tvRuang);
        }
    }
}
