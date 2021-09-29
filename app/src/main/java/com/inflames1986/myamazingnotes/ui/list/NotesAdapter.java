package com.inflames1986.myamazingnotes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final ArrayList<Note> date = new ArrayList<>();

    private OnNoteClickedListener listener;

    private OnNoteLongClickedListener longClickedListener;


    private final Fragment fragment;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setNotes(List<Note> toSet) {
        date.clear();
        date.addAll(toSet);
    }

    public void addNote(Note note) {
        date.add(note);

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
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = date.get(position);

        holder.getTitle().setText(note.getTitle());
        Glide.with(holder.getImage()).load(note.getImage()).
        centerCrop().into(holder.getImage());
        holder.getDesc().setText(note.getDesc());
        holder.getDate().setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public OnNoteClickedListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    public OnNoteLongClickedListener getLongClickedListener() {
        return longClickedListener;
    }

    public void setLongClickedListener(OnNoteLongClickedListener longClickedListener) {
        this.longClickedListener = longClickedListener;
    }

    public int removeNote(Note selectedNote) {


        for (int i = 0; i < date.size(); i++) {
            if (date.get(i).equals(selectedNote)) {
                date.remove(i);
                return i;
            }
        }

        return -1;
    }

    interface OnNoteLongClickedListener {

        void onNoteLongClicked(Note note);

    }

    interface OnNoteClickedListener {

        void onNoteClicked(Note note);

    }


    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView image;
        private final TextView desc;
        private final TextView date;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemView.showContextMenu();

                    if (getLongClickedListener() != null) {
                        getLongClickedListener().onNoteLongClicked(NotesAdapter.this.date.get(getAdapterPosition()));
                    }
                    return true;
                }
            });

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
