package com.example.siakad_ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailInformasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_informasi);

        ImageView ivDetail = findViewById(R.id.ivDetailInformasi);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvDate = findViewById(R.id.tvDateDetail);
        TextView tvContent = findViewById(R.id.tvContentDetail);

        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String content = getIntent().getStringExtra("content");
        String image = getIntent().getStringExtra("image");

        tvTitle.setText(title);
        tvDate.setText(date);
        tvContent.setText(content);
        Glide.with(this).load(image).into(ivDetail);
    }
}
