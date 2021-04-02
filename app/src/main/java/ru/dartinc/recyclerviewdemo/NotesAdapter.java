package ru.dartinc.recyclerviewdemo;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private ArrayList<Note> notes;

    private OnNoteClickListener onNoteClickListener;
    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    interface OnNoteClickListener{
        void onItemClick(int position);
        void onLongClick(int position);
    }



    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new NoteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder  notesViewHolder, int position) {
        Note note = notes.get(position);
        notesViewHolder.textViewTitle.setText(note.getTitle());
        notesViewHolder.textViewDescription.setText(note.getDescription());
        notesViewHolder.textViewDayOfWeek.setText(note.getDayOfWeek());
        int colorId;
        Drawable background;
        int priority = note.getPriority();
        switch (priority){
//            case 1: colorId = notesViewHolder.itemView.getResources().getColor(R.color.green); break;
//            case 2: colorId = notesViewHolder.itemView.getResources().getColor(R.color.yellow); break;
//            case 3: colorId = notesViewHolder.itemView.getResources().getColor(R.color.red); break;
//            default:colorId = notesViewHolder.itemView.getResources().getColor(R.color.black); break;
            case 1: background = notesViewHolder.itemView.getResources().getDrawable(R.drawable.priority_1); break;
            case 2: background = notesViewHolder.itemView.getResources().getDrawable(R.drawable.priority_2); break;
            case 3: background = notesViewHolder.itemView.getResources().getDrawable(R.drawable.priority_3); break;
            default: background = notesViewHolder.itemView.getResources().getDrawable(R.drawable.priority_3);break;
        }
        notesViewHolder.textViewTitle.setBackground(background);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDayOfWeek;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            itemView.setOnClickListener(v -> {
                if(onNoteClickListener!=null){
                    onNoteClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(v -> {
                if(onNoteClickListener!=null){
                    onNoteClickListener.onLongClick(getAdapterPosition());
                }
                return true;
            });
        }
    }
}
