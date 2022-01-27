package org.techtown.todolist;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdpater extends RecyclerView.Adapter<NoteAdpater.ViewHolder> {
    private static final String TAG = "NOteAdapter";

    ArrayList<Note> items = new ArrayList<Note>();

    @NonNull
    @Override
    public NoteAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdpater.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view) {
            super(itemView);
        }
    }
}
