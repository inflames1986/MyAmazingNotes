package com.inflames1986.myamazingnotes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final ArrayList<Note> date = new ArrayList<>();

    public void setNotes(List<Note> toSet) {
        date.clear();
        date.addAll(toSet);


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View noteItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false);
        return new NotesViewHolder(noteItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {

        Note note = date.get(position);

        holder.getTitle().setText(note.getTitle());
        Glide.with(holder.getImage()).load(getString(note.getImage())).into(holder.getImage());
        holder.getDesc().setText(note.getDesc());
        holder.getDate().setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView image;
        private final TextView desc;
        private final TextView date;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            image = itemView.findViewById(R.id.note_image);
            desc = itemView.findViewById(R.id.note_description);
            date = itemView.findViewById(R.id.note_date);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getDesc() {
            return desc;
        }

        public TextView getDate() {
            return date;
        }
    }
}
