package com.inflames1986.myamazingnotes.domain;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

public class MockDeviceNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new FireStoreNotesRepository();

    private Handler handler = new Handler(Looper.getMainLooper());

    private final  ArrayList<Note> notes = new ArrayList<>();

    public MockDeviceNotesRepository() {

        notes.add(new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02"));
        notes.add(new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02"));
        notes.add(new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02"));
        notes.add(new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02"));
        notes.add(new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02"));
    }

    @Override
    public void getNotes(Callback<List<Note>> callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

    @Override
    public void addNote(String title, String image, String desc, String date, Callback<Note> callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Note result = new Note("id1", "Понедельник", "https://img5.goodfon.ru/original/3200x1200/d/a2/osen-listia-fon-doski-colorful-klen-wood-background-autumn-9.jpg", "Заметка 1", "02.03.02");

                notes.add(result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
            }
        }).start();

    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                notes.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        }).start();

    }
}
