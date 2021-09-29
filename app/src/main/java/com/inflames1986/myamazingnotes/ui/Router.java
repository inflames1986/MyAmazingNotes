package com.inflames1986.myamazingnotes.ui;

import androidx.fragment.app.FragmentManager;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.detail.NoteDetailsFragment;
import com.inflames1986.myamazingnotes.ui.edit.EditNoteFragment;
import com.inflames1986.myamazingnotes.ui.list.NotesListFragment;

public class Router {

    private final FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showNotesList() {

        fragmentManager
                .beginTransaction()
                .replace(R.id.details_container, new NotesListFragment())
                .commit();

    }

    public void showNoteDetails(Note note) {

        fragmentManager
                .beginTransaction()
                .replace(R.id.details_container, NoteDetailsFragment.newInstance(note))
                .addToBackStack(null)
                .commit();

    }

    public void showEditNote(Note note) {

        fragmentManager
                .beginTransaction()
                .replace(R.id.details_container, EditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();

    }
}
