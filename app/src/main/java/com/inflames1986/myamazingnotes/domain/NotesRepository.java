package com.inflames1986.myamazingnotes.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void addNote(String title, String image, String desc, String date, Callback<Note> callback);

    void removeNote(Note note, Callback<Void> callback);
}
