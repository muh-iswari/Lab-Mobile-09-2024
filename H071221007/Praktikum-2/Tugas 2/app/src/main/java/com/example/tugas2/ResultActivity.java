package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Mengambil data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nama = extras.getString("kirim_nama");
            String username = extras.getString("kirim_username");
            String gambarUri = extras.getString("gambar");
            String note1 = extras.getString("note 1");
            String note2 = extras.getString("note 2");

            TextView textViewNama = findViewById(R.id.textView5);
            TextView textViewUsername = findViewById(R.id.textView6);
            ImageView imageViewGambar = findViewById(R.id.imageView2);
            textViewNama.setText(nama);
            textViewUsername.setText(username);
            Uri uri = Uri.parse(gambarUri);
            imageViewGambar.setImageURI(uri);

            TextView textViewNote1 = findViewById(R.id.textView7);
            TextView textViewNote2 = findViewById(R.id.textView8);
            textViewNote1.setText(note1);
            textViewNote2.setText(note2);
        }
    }
}
