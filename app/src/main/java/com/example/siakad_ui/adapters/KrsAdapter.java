package com.example.siakad_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.R;
import com.example.siakad_ui.models.Krs;
import java.util.List;

public class KrsAdapter extends RecyclerView.Adapter<KrsAdapter.ViewHolder> {
    private List<Krs> list;

    public KrsAdapter(List<Krs> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_krs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Krs item = list.get(position);
        holder.tvMatkul.setText(item.getMatkul());
        holder.tvKode.setText(item.getKode());
        holder.tvSks.setText(item.getSks() + " SKS");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatkul, tvKode, tvSks;
        ViewHolder(View itemView) {
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvKrsMatkul);
            tvKode = itemView.findViewById(R.id.tvKrsKode);
            tvSks = itemView.findViewById(R.id.tvKrsSks);
        }
    }
}