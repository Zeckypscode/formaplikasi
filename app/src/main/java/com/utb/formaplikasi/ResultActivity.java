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
        TextView tvGender = findViewById(R.id.tvResultGender);
        Button btnBack = findViewById(R.id.btnBack);

        // Mengambil data dari Intent
        String nama = getIntent().getStringExtra("NAMA");
        String gender = getIntent().getStringExtra("GENDER");
        String email = getIntent().getStringExtra("EMAIL");
        String phone = getIntent().getStringExtra("PHONE");
        String seminar = getIntent().getStringExtra("SEMINAR");

        // Menampilkan data ke TextView dengan proteksi null
        tvNama.setText(getString(R.string.result_name, nama != null ? nama : "-"));

        // PERBAIKAN DI SINI: Menyertakan variabel gender ke dalam string resource
        tvGender.setText(getString(R.string.gender_gender, gender != null ? gender : "-"));

        tvEmail.setText(getString(R.string.result_email, email != null ? email : "-"));
        tvPhone.setText(getString(R.string.result_phone, phone != null ? phone : "-"));
        tvSeminar.setText(getString(R.string.result_seminar, seminar != null ? seminar : "-"));

        // Tombol kembali sesuai instruksi UTS
        btnBack.setOnClickListener(v -> finish());
    }
}