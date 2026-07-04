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
import com.example.siakad_ui.models.Informasi;
import java.util.List;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.ViewHolder> {
    private List<Informasi> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Informasi item);
    }

    public InformasiAdapter(List<Informasi> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Informasi item = list.get(position);
        holder.tvTitle.setText(item.getJudul());
        holder.tvDate.setText(item.getTanggal());
        Glide.with(holder.itemView.getContext()).load(item.getGambar()).into(holder.ivInfo);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivInfo;
        TextView tvTitle, tvDate;
        ViewHolder(View itemView) {
            super(itemView);
            ivInfo = itemView.findViewById(R.id.ivInfo);
            tvTitle = itemView.findViewById(R.id.tvInfoTitle);
            tvDate = itemView.findViewById(R.id.tvInfoDate);
        }
    }
}