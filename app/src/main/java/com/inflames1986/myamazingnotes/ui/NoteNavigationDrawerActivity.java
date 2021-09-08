package com.inflames1986.myamazingnotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsActivity;
import com.inflames1986.myamazingnotes.ui.details.NoteDetailsFragment;
import com.inflames1986.myamazingnotes.ui.list.NotesListFragment;

public class NoteNavigationDrawerActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_navigation_drawer);

        Toolbar toolbar = findViewById(R.id.note_toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.note_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.note_navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mainnotes) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Main Notes", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.sportNotes) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Sport Notes", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.workNotes) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Work Notes", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.healthNotes) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Health Notes", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.familyNotes) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Family Notes", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if (item.getItemId() == R.id.mtrl_calendar_day_selector_frame) {
                    Toast.makeText(NoteNavigationDrawerActivity.this, "Calendar", Toast.LENGTH_SHORT).show();
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
                    .addToBackStack(null)
                    .commit();

        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}