package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    EditText note1, note2;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        note1 = findViewById(R.id.firstNote);
        note2 = findViewById(R.id.secondNote);
        saveButton = findViewById(R.id.button2);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teks1 = note1.getText().toString().trim();
                String teks2 = note2.getText().toString().trim();

                Intent intent = getIntent();
                if (teks1.length() == 0){
                    note1.setError("Wajib diisi");
                } else if (teks2.length() == 0){
                    note2.setError("Wajib diisi");
                } else {
                    Intent intent2 = new Intent(NoteActivity.this, ResultActivity.class);
                    intent2.putExtras(intent);
                    intent2.putExtra("note 1", teks1);
                    intent2.putExtra("note 2", teks2);
                    startActivity(intent2);
                }
            }
        });
    }
}
