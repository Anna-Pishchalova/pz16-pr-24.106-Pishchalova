package com.example.pz16;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private final String SAVED_TEXT = "saved_text";
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editext);
        Button btnSave = findViewById(R.id.save);
        Button btnLoad = findViewById(R.id.load);
        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        sharedPreferences = getPreferences(MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save) {
            saveText();
        } else if (id == R.id.load) {
            loadText();
        }
    }

    private void saveText() {
        String text = editText.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show();
            return;
        }
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, text);
        Toast.makeText(this, "Текст сохранён", Toast.LENGTH_SHORT).show();
    }
    private void loadText() {
        String savedText = sharedPreferences.getString(SAVED_TEXT, "");
        editText.setText(savedText);

        if (savedText.isEmpty()) {
            Toast.makeText(this, "Нет сохранённого текста", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Текст загружен", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}