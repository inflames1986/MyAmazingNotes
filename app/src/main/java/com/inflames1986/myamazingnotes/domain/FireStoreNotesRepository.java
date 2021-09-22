package com.inflames1986.myamazingnotes.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FireStoreNotesRepository implements NotesRepository {

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

                                result.add(new Note(document.getId(), title, image, desc, date));
                            }
                            callback.onSuccess(result);
                        } else {

                        }
                    }
                });
    }

    @Override
    public void addNote(String title, String image, String desc, String date, Callback<Note> callback) {

        HashMap<String, Object> data = new HashMap<>();

        data.put(TITLE, title);
        data.put(IMAGE, image);
        data.put(DESC, desc);
        data.put(DATE, date);

        db.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            String noteId = Objects.requireNonNull(task.getResult()).getId();

                            callback.onSuccess(new Note(noteId, title, image, desc, date));
                        }
                    }
                });
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

        db.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);

                    }
                });
    }
}
