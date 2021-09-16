package com.inflames1986.myamazingnotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsActivity;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsFragment;
import com.inflames1986.myamazingnotes.ui.list.NotesListFragment;
import com.inflames1986.myamazingnotes.ui.list.NotesListPresenter;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    private NotesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.note_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.clear_all) {
                    NotesListFragment.adapter.setNotes(Collections.emptyList());
                    NotesListFragment.adapter.notifyDataSetChanged();
                    return true;
                }

                if (item.getItemId() == R.id.action_add) {
                        NotesListFragment.presenter.addNote(R.string.note_monday, R.string.image_url, R.string.descThueday, R.string.firstJan);
                        return true;
                    }
                return false;

            }
        });
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