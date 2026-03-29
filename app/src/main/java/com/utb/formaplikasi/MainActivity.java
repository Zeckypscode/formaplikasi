package com.utb.formaplikasi;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi View
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        TextInputLayout tilEmail = findViewById(R.id.tilEmail);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        Spinner spProvince = findViewById(R.id.spProvince);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        CheckBox cbCoding = findViewById(R.id.cbCoding);
        CheckBox cbGaming = findViewById(R.id.cbGaming);
        CheckBox cbMusic = findViewById(R.id.cbMusic);
        CheckBox cbTravel = findViewById(R.id.cbTravel);

        // Setup Spinner
        String[] provinces = {"West Java", "Jakarta", "Central Java", "East Java", "Bali"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinces);
        spProvince.setAdapter(adapter);

        // Submit Action
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();

            // Hitung hobby yang dipilih
            int hobbyCount = 0;
            if (cbCoding.isChecked()) hobbyCount++;
            if (cbGaming.isChecked()) hobbyCount++;
            if (cbMusic.isChecked()) hobbyCount++;
            if (cbTravel.isChecked()) hobbyCount++;

            // Validasi Sederhana
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.setError("Invalid Email");
            } else if (hobbyCount < 3) {
                Toast.makeText(this, "Select at least 3 hobbies", Toast.LENGTH_SHORT).show();
            } else {
                showConfirmationDialog(name);
            }
        });

        // Long Press Gesture
        btnSubmit.setOnLongClickListener(v -> {
            Toast.makeText(this, "Form Cleared!", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etEmail.setText("");
            return true;
        });
    }

    private void showConfirmationDialog(String name) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Register as " + name + "?")
                .setPositiveButton("Yes", (dialog, which) ->
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show())
                .setNegativeButton("No", null)
                .show();
    }
}