package com.inflames1986.myamazingnotes.ui.list;

import com.inflames1986.myamazingnotes.domain.Callback;
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
//        view.showProgress();

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                view.showNotes(data);

//                view.hideProgress();
            }
        });
    }
}
