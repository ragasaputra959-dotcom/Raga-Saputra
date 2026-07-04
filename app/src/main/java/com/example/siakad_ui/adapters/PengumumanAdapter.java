package com.example.siakad_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.R;
import com.example.siakad_ui.models.Pengumuman;
import java.util.List;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {
    private final List<Pengumuman> list;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Pengumuman item);
    }

    public PengumumanAdapter(List<Pengumuman> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengumuman, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pengumuman item = list.get(position);
        holder.tvTitle.setText(item.getJudul());
        holder.tvDate.setText(item.getTanggal());
        holder.tvShort.setText(item.getKonten()); // Menggunakan getKonten untuk tvShort
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvShort; // Sesuaikan dengan ID di XML
        ViewHolder(View itemView) {
            super(itemView);
            // ID di sini harus sesuai dengan yang ada di item_pengumuman.xml
            tvTitle = itemView.findViewById(R.id.tvPengumumanTitle);
            tvDate = itemView.findViewById(R.id.tvPengumumanDate);
            tvShort = itemView.findViewById(R.id.tvPengumumanShort);
        }
    }
}
