package com.inflames1986.myamazingnotes.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NoteDetailsFragment newInstance(Note note) {

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(ARG_NOTE, note);

        fragment.setArguments(arguments);
        return fragment;
    }

    public NoteDetailsFragment() {

        super(R.layout.fragment_note_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note note = getArguments().getParcelable(ARG_NOTE);

        TextView noteName = view.findViewById(R.id.note_name);
        noteName.setText(note.getNoteName());

        TextView noteDesc = view.findViewById(R.id.note_desc);
        noteDesc.setText(note.getNoteDesc());

        TextView noteDate = view.findViewById(R.id.note_date);
        noteDate.setText(note.getNoteDate());
    }
}
