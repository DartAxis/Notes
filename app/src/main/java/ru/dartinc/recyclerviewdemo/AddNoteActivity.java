package ru.dartinc.recyclerviewdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDayOfWeek;
    private int priority = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerDayOfWeek = findViewById(R.id.spinnerDayOfWeek);
    }

    public void onClickSaveAddNote(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String dayOfWeek = spinnerDayOfWeek.getSelectedItem().toString();
        MainActivity.notes.add(new Note(title, description, dayOfWeek, priority));
        startActivity(intent);
    }

    public void onClickRb(View view) {
        priority = Integer.parseInt(view.getTag().toString());
    }
}