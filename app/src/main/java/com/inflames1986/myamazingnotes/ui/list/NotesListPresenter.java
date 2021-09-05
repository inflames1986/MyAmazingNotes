package com.inflames1986.myamazingnotes.ui.list;

import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private NotesListView view;

    private NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {

        List<Note> result = repository.getNotes();

        view.showNotes(result);
    }
}
