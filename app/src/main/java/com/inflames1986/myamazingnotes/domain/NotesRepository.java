package com.inflames1986.myamazingnotes.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void addNote(int title, int image, int desc, int date, Callback<Note> callback);
}
