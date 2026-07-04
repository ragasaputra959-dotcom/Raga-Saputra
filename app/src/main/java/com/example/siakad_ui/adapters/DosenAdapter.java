package com.example.siakad_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.siakad_ui.R;
import com.example.siakad_ui.models.Dosen;
import java.util.List;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {
    private List<Dosen> list;

    public DosenAdapter(List<Dosen> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dosen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dosen item = list.get(position);
        holder.tvName.setText(item.getNama());
        holder.tvNidn.setText("NIDN. " + item.getNidn());
        holder.tvMatkul.setText(item.getMatkul());
        Glide.with(holder.itemView.getContext()).load(item.getFoto()).placeholder(android.R.drawable.sym_def_app_icon).into(holder.ivDosen);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDosen;
        TextView tvName, tvNidn, tvMatkul;
        ViewHolder(View itemView) {
            super(itemView);
            ivDosen = itemView.findViewById(R.id.ivDosen);
            tvName = itemView.findViewById(R.id.tvDosenName);
            tvNidn = itemView.findViewById(R.id.tvDosenNidn);
            tvMatkul = itemView.findViewById(R.id.tvDosenMatkul);
        }
    }
}