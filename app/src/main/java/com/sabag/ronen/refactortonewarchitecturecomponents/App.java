package com.sabag.ronen.refactortonewarchitecturecomponents;

import android.app.Application;

import com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl.NotesTable;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.INotesTable;

public class App extends Application {
    private NotesTable mNotesTable;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotesTable = new NotesTable(this);
        mNotesTable.open();
    }

    public INotesTable getNotesTable() {
        return mNotesTable;
    }
}
