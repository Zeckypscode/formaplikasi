package com.utb.formaplikasi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPhone, tilPassword, tilConfirmPassword;
    private EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    private RadioGroup rgGender;
    private CheckBox cbCoding, cbGaming, cbMusic, cbTravel, cbAgreement;
    private Button btnSubmit;

    // 1. Deklarasi variabel Spinner yang Anda maksud
    private Spinner pilihanSeminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinner(); // Panggil tanpa parameter yang membingungkan
        setupListeners();
        setupRealTimeValidation();
    }

    private void initViews() {
        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhone = findViewById(R.id.tilPhone);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        rgGender = findViewById(R.id.rgGender);
        cbCoding = findViewById(R.id.cbCoding);
        cbGaming = findViewById(R.id.cbGaming);
        cbMusic = findViewById(R.id.cbMusic);
        cbTravel = findViewById(R.id.cbTravel);
        cbAgreement = findViewById(R.id.cbAgreement);
        btnSubmit = findViewById(R.id.btnSubmit);

        // 2. HUBUNGKAN KE XML (Ganti R.id.spProvince dengan ID Spinner di XML Anda)
        pilihanSeminar = findViewById(R.id.spPilihan_Seminar);
    }

    // 3. Perbaikan fungsi setupSpinner
    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pilihan_seminar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihanSeminar.setAdapter(adapter);
    }

    private void setupListeners() {
        btnSubmit.setOnClickListener(v -> performSubmission());
        btnSubmit.setOnLongClickListener(v -> {
            resetForm();
            return true;
        });
    }

    private void performSubmission() {
        if (isFormValid()) {
            showConfirmationDialog();
        }
    }

    private void setupRealTimeValidation() {
        TextWatcher passwordWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();
                if (!confirm.isEmpty() && !pass.equals(confirm)) {
                    tilConfirmPassword.setError(getString(R.string.error_pass_match));
                } else {
                    tilConfirmPassword.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        };
        etPassword.addTextChangedListener(passwordWatcher);
        etConfirmPassword.addTextChangedListener(passwordWatcher);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                if (!input.isEmpty() && !input.startsWith("08")) {
                    tilPhone.setError(getString(R.string.error_phone_prefix));
                } else {
                    tilPhone.setError(null);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private boolean isFormValid() {
        boolean valid = true;
        if (etName.getText().toString().trim().isEmpty()) {
            tilName.setError(getString(R.string.error_empty));
            valid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            tilEmail.setError(getString(R.string.error_email));
            valid = false;
        }
        String phone = etPhone.getText().toString().trim();
        if (phone.isEmpty()) {
            tilPhone.setError(getString(R.string.error_empty));
            valid = false;
        } else if (!phone.startsWith("08") || phone.length() < 10 || phone.length() > 13) {
            tilPhone.setError(getString(R.string.error_phone_length));
            valid = false;
        }
        if (getSelectedHobbiesCount() < 3) {
            Toast.makeText(this, getString(R.string.error_hobbies), Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (!cbAgreement.isChecked()) {
            Toast.makeText(this, getString(R.string.error_checkbox), Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    private int getSelectedHobbiesCount() {
        int count = 0;
        CheckBox[] checks = {cbCoding, cbGaming, cbMusic, cbTravel};
        for (CheckBox cb : checks) if (cb.isChecked()) count++;
        return count;
    }

    private void showConfirmationDialog() {
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        String gender = "Tidak dipilih";
        if (selectedGenderId != -1) {
            RadioButton rb = findViewById(selectedGenderId);
            gender = rb.getText().toString();
        }

        final String finalGender = gender;

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton(getString(R.string.yes), (d, w) -> {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("NAMA", etName.getText().toString());
                    intent.putExtra("EMAIL", etEmail.getText().toString());
                    intent.putExtra("PHONE", etPhone.getText().toString());
                    intent.putExtra("GENDER", finalGender);
                    // Ambil data dari variabel pilihanSeminar
                    intent.putExtra("SEMINAR", pilihanSeminar.getSelectedItem().toString());
                    startActivity(intent);
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    private void resetForm() {
        EditText[] fields = {etName, etEmail, etPhone, etPassword, etConfirmPassword};
        for (EditText f : fields) f.setText("");
        TextInputLayout[] layouts = {tilName, tilEmail, tilPhone, tilPassword, tilConfirmPassword};
        for (TextInputLayout l : layouts) l.setError(null);
        rgGender.clearCheck();
        cbCoding.setChecked(false);
        cbGaming.setChecked(false);
        cbMusic.setChecked(false);
        cbTravel.setChecked(false);
        cbAgreement.setChecked(false);

        // Reset Spinner
        if (pilihanSeminar != null) pilihanSeminar.setSelection(0);

        Toast.makeText(this, "Form dibersihkan", Toast.LENGTH_SHORT).show();
    }
}