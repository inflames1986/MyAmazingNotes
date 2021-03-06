package com.inflames1986.myamazingnotes.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inflames1986.myamazingnotes.R;
import com.inflames1986.myamazingnotes.domain.DeviceNotesRepository;
import com.inflames1986.myamazingnotes.domain.Note;
import com.inflames1986.myamazingnotes.ui.Router;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public interface OnNoteClicked {
        void onNoteOnClicked(Note note);
    }

    private Router router;

    public static NotesListPresenter presenter;

    public static NotesAdapter adapter;

    private ProgressBar progressBar;

    private OnNoteClicked onNoteClicked;

    private RecyclerView notesList;

    private Note selectedNote;


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

        router = new Router(getChildFragmentManager());

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

        if (savedInstanceState == null) {
            router.showNotesList();
        }

        adapter.setLongClickedListener(new NotesAdapter.OnNoteLongClickedListener() {
            @Override
            public void onNoteLongClicked(Note note) {

                selectedNote = note;

            }
        });

        notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        notesList.setAdapter(adapter);


        presenter.requestNotes();

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(1000L);

        notesList.setItemAnimator(animator);
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

    @Override
    public void onNoteRemoved(Note selectedNote) {
        int index = adapter.removeNote(selectedNote);

        adapter.notifyItemRemoved(index);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {

            presenter.removeNote(selectedNote);
            return true;
        }
        if (item.getItemId() == R.id.action_update) {
            Toast.makeText(requireContext(), "Update " + getString(selectedNote.getTitle()), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}