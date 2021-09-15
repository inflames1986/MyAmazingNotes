package com.inflames1986.myamazingnotes.domain;

import android.os.Handler;
import android.os.Looper;

import com.inflames1986.myamazingnotes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getNotes(Callback<List<Note>> callback) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<Note> notes = new ArrayList<>();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                notes.add(new Note(R.string.note_monday, R.string.image_url, R.string.descThueday, R.string.firstJan));
                notes.add(new Note(R.string.note_tuesday, R.string.image_url2, R.string.descTuesday, R.string.secondJan));
                notes.add(new Note(R.string.note_wednesday, R.string.image_url3, R.string.descWednesday, R.string.thirdJan));
                notes.add(new Note(R.string.note_thursday, R.string.image_url4, R.string.descThursday, R.string.fourthJan));
                notes.add(new Note(R.string.note_friday, R.string.image_url5, R.string.descFriday, R.string.fifthJan));

                for (int i = 0; i < 10; i++) {

                    notes.add(new Note(R.string.note_friday, R.string.image_url5, R.string.descFriday, R.string.fifthJan));
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });
            }
        }).start();
    }
}
