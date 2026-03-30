# Tugas Mobile Programming - Week 5
Aplikasi Form Registrasi With Validation real time code

## Identitas Mahasiswa
- NAMA : MOCHAMAD ZAKY AKBAR SOFYAN
- NIM : 24552011323
- KELAS : TIF 24 CID C

## Screenshot Aplikasi
<img width="815" height="705" alt="SCREENSHOOT" src="https://github.com/user-attachments/assets/169dab8e-fd06-4d61-b11f-154287a8c2e1" />

## Video Demo Aplikasi
https://github.com/user-attachments/assets/d23145fc-ed15-4b28-9042-c26ca8f554bc

## Deskripsi Hasil Akhir

## Fitur & Komponen Teknis
* **Data Input:** Menggunakan `EditText` dengan tipe input yang disesuaikan (Text & Number).
* **Explicit Intent:** Mekanisme perpindahan halaman dari `MainActivity` ke `TampilDataActivity`.
* **Data Bundling:** Pengiriman data string dan integer menggunakan metode `.putExtra()` dan pengambilan data melalui `.getStringExtra()`.
* **Responsive Layout:** Menggunakan `ConstraintLayout` agar tampilan tetap presisi di berbagai ukuran layar smartphone.
* TextWatcher Implementation: Menggunakan interface TextWatcher untuk memantau setiap karakter yang diketik pengguna secara langsung (real-time).

Instant Feedback: Memberikan peringatan berupa pesan error (misalnya: "Password minimal 8 karakter") secara instan melalui setError() pada komponen EditText sebelum pengguna menekan tombol submit.

Logic Criteria: Validasi mencakup pengecekan panjang karakter dan kesesuaian format untuk memastikan integritas data.


## Cara Penggunaan

1.  **Input Data:** Masukkan Nama Lengkap, NIM, dan Email pada kolom yang tersedia di halaman utama.
2.  **Validasi:** Pastikan semua kolom sudah terisi (Aplikasi akan memproses data jika input valid).
3.  **Submit:** Klik tombol **"Kirim Data"** atau **"Proses"**.
4.  **Halaman Hasil:** Aplikasi akan berpindah ke Activity kedua yang menampilkan seluruh informasi yang telah diinput sebelumnya.
5.  **Kembali:** Klik tombol **"Kembali"** atau tombol back pada sistem Android untuk mengulangi proses input.
