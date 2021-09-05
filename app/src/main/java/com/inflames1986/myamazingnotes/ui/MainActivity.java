package com.inflames1986.myamazingnotes.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsActivity;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsFragment;
import com.inflames1986.myamazingnotes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteOnClicked(Note note) {

        if (getResources().getBoolean(R.bool.isLandscape)) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.details_container, NoteDetailsFragment.newInstance(note), null)
                    .commit();
            
        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}