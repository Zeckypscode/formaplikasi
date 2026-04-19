package com.utb.formaplikasi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Inisialisasi View
        TextView tvNama = findViewById(R.id.tvResultNama);
        TextView tvEmail = findViewById(R.id.tvResultEmail);
        TextView tvPhone = findViewById(R.id.tvResultPhone);
        TextView tvSeminar = findViewById(R.id.tvResultSeminar);
        Button btnBack = findViewById(R.id.btnBack);

        // Mengambil data dari Intent
        String nama = getIntent().getStringExtra("NAMA");
        String email = getIntent().getStringExtra("EMAIL");
        String phone = getIntent().getStringExtra("PHONE");
        String seminar = getIntent().getStringExtra("SEMINAR");

        // Menampilkan data ke TextView

        tvNama.setText(getString(R.string.result_name, nama));
        tvEmail.setText(getString(R.string.result_email, email));
        tvPhone.setText(getString(R.string.result_phone, phone));
        tvSeminar.setText(getString(R.string.result_seminar, seminar));

        // Tombol kembali sesuai instruksi UTS
        btnBack.setOnClickListener(v -> finish());
    }
}