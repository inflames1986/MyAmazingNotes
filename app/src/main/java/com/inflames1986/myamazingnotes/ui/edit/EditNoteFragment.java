package com.inflames1986.myamazingnotes.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.Note;

public class EditNoteFragment extends Fragment {


    public EditNoteFragment() {
        // Required empty public constructor
    }


    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }
}