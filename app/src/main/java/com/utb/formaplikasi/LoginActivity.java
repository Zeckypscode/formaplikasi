package com.utb.formaplikasi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Nama file XML Login Anda

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            // Logika Login Sederhana
            if (user.equals("admin") && pass.equals("admin123")) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show();

                // PINDAH KE FORM REGISTRASI (MainActivity yang Anda buat)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Tutup halaman login agar tidak bisa back ke sini
            } else {
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}