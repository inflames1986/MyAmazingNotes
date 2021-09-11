package com.inflames1986.myamazingnotes.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.DeviceNotesRepository;
import com.inflames1986.myamazingnotes.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public interface OnNoteClicked {
        void onNoteOnClicked(Note note);
    }

    private NotesListPresenter presenter;

    private LinearLayout container;

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.root);

        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {

        for (Note note : notes) {

            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note_list, container, false);

            TextView title = noteItem.findViewById(R.id.note_title);
            title.setText(note.getTitle());

            ImageView image = noteItem.findViewById(R.id.note_image);
            Glide.with(this).load(getString(note.getImage())).into(image);

            TextView desc = noteItem.findViewById(R.id.note_description);
            desc.setText(note.getDesc());

            TextView date = noteItem.findViewById(R.id.note_date);
            date.setText(note.getDate());

            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteOnClicked(note);
                    }
                }
            });

            TextView noteName = title;

            noteName.setText(note.getTitle());

            container.addView(noteItem);
        }
    }
}
