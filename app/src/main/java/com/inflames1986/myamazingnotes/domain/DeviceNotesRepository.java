package com.inflames1986.myamazingnotes.domain;

import com.inflames1986.myamazingnotes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {
    @Override
    public List<Note> getNotes() {

        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note(R.string.note_monday, R.string.image_url, R.string.descThueday, R.string.firstJan));
        notes.add(new Note(R.string.note_tuesday, R.string.image_url2, R.string.descTuesday, R.string.secondJan));
        notes.add(new Note(R.string.note_wednesday, R.string.image_url3, R.string.descWednesday, R.string.thirdJan));
        notes.add(new Note(R.string.note_thursday, R.string.image_url4, R.string.descThursday, R.string.fourthJan));
        notes.add(new Note(R.string.note_friday, R.string.image_url5, R.string.descFriday, R.string.fifthJan));

        return notes;
    }
}
