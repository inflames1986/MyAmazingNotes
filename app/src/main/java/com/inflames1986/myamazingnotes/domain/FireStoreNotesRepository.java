package com.inflames1986.myamazingnotes.domain;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireStoreNotesRepository implements NotesRepository{

    public static final String NOTES = "notes";
    public static final String TITLE = "title";
    public static final String IMAGE = "image";
    public static final String DESC = "desc";
    public static final String DATE = "date";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {

        db.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        ArrayList<Note> result = new ArrayList<>();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String title = document.get(TITLE, String.class);
                                String image = document.get(IMAGE, String.class);
                                String desc = document.get(DESC, String.class);
                                String date = document.get(DATE, String.class);

                                result.add(new Note(document.getId(), )

                            }
                        } else {

                        }
                    }
                });
    }

    @Override
    public void addNote(int title, int image, int desc, int date, Callback<Note> callback) {

    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

    }
}
