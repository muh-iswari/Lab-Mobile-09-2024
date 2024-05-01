package com.example.tugas1lab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private LinearLayout linearLayout;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxGame, checkBoxMoney, checkBoxOther;
    private EditText editTextOther;
    private Button buttonOk;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        Button button = findViewById(R.id.button);
        linearLayout = findViewById(R.id.linearLayout);

        radioButtonMale = findViewById(R.id.radioButton);
        radioButtonFemale = findViewById(R.id.radioButton2);
        checkBoxGame = findViewById(R.id.checkBox);
        checkBoxMoney = findViewById(R.id.checkBox3);
        checkBoxOther = findViewById(R.id.checkBox4);
        editTextOther = findViewById(R.id.editTextText);
        buttonOk = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText();
            }
        });

        // Menetapkan OnClickListener untuk RadioButton laki-laki
        radioButtonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pastikan RadioButton perempuan tidak tercentang
                radioButtonFemale.setChecked(false);
            }
        });

        // Menetapkan OnClickListener untuk RadioButton perempuan
        radioButtonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pastikan RadioButton laki-laki tidak tercentang
                radioButtonMale.setChecked(false);
            }
        });
    }

    private void addText() {
        String newText = editText.getText().toString().trim();
        if (!newText.isEmpty()) {
            EditText textView = new EditText(this);
            textView.setText(newText);
            textView.setEnabled(false);
            linearLayout.addView(textView);
            editText.getText().clear();
        }
    }
}
