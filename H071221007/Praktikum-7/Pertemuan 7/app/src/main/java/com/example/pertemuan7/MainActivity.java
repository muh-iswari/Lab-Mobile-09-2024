package com.example.pertemuan7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    Button btn_login, btn_register;
    EditText et_nim, et_password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nim = findViewById(R.id.et_nim);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        // Memeriksa status login saat aplikasi dibuka
        checkLoginStatus();

        btn_register.setOnClickListener(view -> {
            // Buka RegisterActivity jika tombol register diklik
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(view -> {
            // Mendapatkan nilai NIM dan password dari EditText
            String nim = et_nim.getText().toString().trim();
            String password = et_password.getText().toString().trim();

            // Memeriksa apakah input tidak kosong dan valid
            if (!nim.isEmpty() && !password.isEmpty()) {
                // Melakukan validasi login
                boolean isValid = isValidLogin(nim, password);
                if (isValid) {
                    // Jika login berhasil, simpan status login dan arahkan ke LoginActivity
                    saveLoginStatus(true);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Selesaikan MainActivity
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please enter NIM and password", Toast.LENGTH_SHORT).show();
            }
        });

        // Pengaturan tema berdasarkan preferensi yang tersimpan
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false);
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Method untuk memeriksa status login
    private void checkLoginStatus() {
        sharedPreferences = this.getSharedPreferences("user_pref", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // Jika pengguna sudah login, arahkan ke LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Selesaikan MainActivity
        }
    }

    // Method untuk validasi login
    private boolean isValidLogin(String nim, String password) {
        // Di sini Anda dapat melakukan validasi login dengan memeriksa NIM dan password
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString("password", "");
        String storedNim = sharedPreferences.getString("nim", "");

        return storedNim.equals(nim) && storedPassword.equals(password);
    }

    // Method untuk menyimpan status login ke SharedPreferences
    private void saveLoginStatus(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
}
