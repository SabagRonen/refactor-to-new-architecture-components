package com.sabag.ronen.refactortonewarchitecturecomponents.notesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sabag.ronen.refactortonewarchitecturecomponents.App;
import com.sabag.ronen.refactortonewarchitecturecomponents.addNotes.AddNoteDialog;
import com.sabag.ronen.refactortonewarchitecturecomponents.addNotes.AddNoteListener;
import com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails.NoteDetailsActivity;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.INotesTable;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl.NoteData;
import com.sabag.ronen.refactortonewarchitecturecomponents.R;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements AddNoteListener, OnNoteClickListener {

    private INotesTable mNotesTable;
    private RecyclerView notesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notesList = findViewById(R.id.notesList);
        notesList.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        notesList.setAdapter(new NotesAdapter(this));

        View fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNoteDialog addNoteDialog = new AddNoteDialog();
                addNoteDialog.show(getSupportFragmentManager(), "AddNoteDialog");
            }
        });

        mNotesTable = ((App)getApplication()).getNotesTable();
        updateNotes(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClick(long noteId) {
        Intent intent = new Intent(this, NoteDetailsActivity.class);
        intent.putExtra("NOTE_ID", noteId);
        startActivity(intent);
    }

    @Override
    public void onNoteAdded(String note) {
        mNotesTable.add(new NoteData(note));
        updateNotes(false);
    }

    private void updateNotes(boolean notifyAll) {
        List<NoteData> all = mNotesTable.getAll();
        NotesAdapter adapter = (NotesAdapter) notesList.getAdapter();
        adapter.setNotes(all);
        if (notifyAll) {
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyItemInserted(all.size() - 1);
        }
    }
}