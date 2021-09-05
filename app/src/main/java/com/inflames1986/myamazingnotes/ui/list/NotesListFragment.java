package com.inflames1986.myamazingnotes.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.DeviceNotesRepository;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsActivity;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    private NotesListPresenter presenter;

    private LinearLayout container;

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

        for (Note note: notes) {

            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);

            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(requireContext(), NoteDetailsActivity.class);
                    intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
                    startActivity(intent);
                }
            });

            TextView noteName = noteItem.findViewById(R.id.note_name);

            noteName.setText(note.getNoteName());

            container.addView(noteItem);
        }
    }
}
