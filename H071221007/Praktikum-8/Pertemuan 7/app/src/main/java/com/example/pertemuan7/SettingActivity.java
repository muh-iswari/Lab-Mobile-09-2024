package com.example.pertemuan7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    Switch sw_tema;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DARK_MODE = "darkMode";
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sw_tema = findViewById(R.id.sw_tema);
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false);
        sw_tema.setChecked(isDarkTheme);

        sw_tema.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Memeriksa apakah Switch diaktifkan
            if (isChecked) {
                setDarkTheme();
            } else {
                setLightTheme();
            }
            // Menyimpan status tema yang dipilih
            editor.putBoolean("is_dark_theme", isChecked);
            editor.apply();
        });
    }

    private void setDarkTheme() {
        // Mengatur tema aplikasi ke tema gelap
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void setLightTheme() {
        // Mengatur tema aplikasi ke tema terang
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}