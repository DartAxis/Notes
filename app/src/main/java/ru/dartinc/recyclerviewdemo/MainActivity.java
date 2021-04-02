package ru.dartinc.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNote;
    public static final ArrayList<Note> notes = new ArrayList<>();
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        recyclerViewNote = findViewById(R.id.recycleViewNotes);
        if(notes.isEmpty()){
            fillNoteList();
        }
        adapter = new NotesAdapter(notes);
        recyclerViewNote.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNote.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLongClick(int position) {
                removeItem(position);
            }
        });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem(viewHolder.getAdapterPosition());
            }
        });
        touchHelper.attachToRecyclerView(recyclerViewNote);
    }

    private void removeItem(int position){
        notes.remove(position);
        adapter.notifyDataSetChanged();
        if(notes.isEmpty()){
            Toast.makeText(MainActivity.this, "Список пуст!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillNoteList() {
        notes.add(new Note("Парикмахер","Сделать причёску","Понедельник",1));
        notes.add(new Note("Банк","Погасить очередной платеж по кредиту","Понедельник",3));
        notes.add(new Note("Магазин","Купить продуктов","Вторник",2));
        notes.add(new Note("Страховка","Получить страховку на втомобиль","Среда",3));
        notes.add(new Note("Магазин","Купить компьютерный стол и стул","Среда",1));
        notes.add(new Note("Спортзал","Сходить на тренировку","Четверг",1));
        notes.add(new Note("Поздравления","Поздравить босса с днюхой","Пятница",3));
        notes.add(new Note("Вечер пятницы","Нажраться в слюни","Пятница",3));
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this,AddNoteActivity.class);
        startActivity(intent);
    }
}