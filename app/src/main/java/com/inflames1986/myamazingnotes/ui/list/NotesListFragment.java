package com.inflames1986.myamazingnotes.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.DeviceNotesRepository;
import com.inflames1986.myamazingnotes.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public interface OnNoteClicked {
        void onNoteOnClicked(Note note);
    }

    public static NotesListPresenter presenter;

    public static NotesAdapter adapter;

    private ProgressBar progressBar;

    private OnNoteClicked onNoteClicked;

    private RecyclerView notesList;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, new DeviceNotesRepository());

        adapter = new NotesAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        notesList.setAdapter(adapter);


        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onNoteAdded(Note note) {
        adapter.addNote(note);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);

        notesList.smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}