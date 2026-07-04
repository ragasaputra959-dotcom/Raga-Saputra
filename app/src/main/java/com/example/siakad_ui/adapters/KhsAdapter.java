package com.example.siakad_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.siakad_ui.R;
import com.example.siakad_ui.models.Khs;
import java.util.List;

public class KhsAdapter extends RecyclerView.Adapter<KhsAdapter.ViewHolder> {
    private List<Khs> list;

    public KhsAdapter(List<Khs> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Khs item = list.get(position);
        holder.tvMatkul.setText(item.getMatkul());
        holder.tvSks.setText(item.getSks() + " SKS");
        holder.tvNilai.setText(item.getNilai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatkul, tvSks, tvNilai;
        ViewHolder(View itemView) {
            super(itemView);
            tvMatkul = itemView.findViewById(R.id.tvKhsMatkul);
            tvSks = itemView.findViewById(R.id.tvKhsSks);
            tvNilai = itemView.findViewById(R.id.tvKhsNilai);
        }
    }
}