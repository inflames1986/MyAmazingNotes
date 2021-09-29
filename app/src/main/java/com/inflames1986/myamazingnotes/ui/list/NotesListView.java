package com.inflames1986.myamazingnotes.ui.list;

import com.inflames1986.myamazingnotes.domain.Note;

import java.util.List;

public interface NotesListView {

    void showNotes(List<Note> notes);

    void showProgress();

    void hideProgress();

    void onNoteAdded(Note note);

    void onNoteRemoved(Note selectedNote);
}
