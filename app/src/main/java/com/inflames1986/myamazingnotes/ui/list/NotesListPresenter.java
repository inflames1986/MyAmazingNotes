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

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                view.showNotes(data);
            }
        });
    }

    public void addNote(String title, String image, String desc, String date) {

        repository.addNote("Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "заметка заметка", "23.12.2021", new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {
                view.onNoteAdded(data);
            }
        });
    }

    public void removeNote(Note selectedNote) {

        repository.removeNote(selectedNote, new Callback<Void>() {
            @Override
            public void onSuccess(Void data) {
                view.onNoteRemoved(selectedNote);
            }
        });
    }
}
