package com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails;

import com.sabag.ronen.refactortonewarchitecturecomponents.storage.NoteData;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.NotesTable;

class NoteDetailsModel {

    private final NotesTable mNotesTable;

    NoteDetailsModel(NotesTable notesTable) {
        mNotesTable = notesTable;
        mNotesTable.open();
    }

    NoteData getNote(long noteId) {
        return mNotesTable.get(noteId);
    }

    void deleteNote(long noteId) {
        mNotesTable.delete(noteId);
    }

    void updateNote(long noteId, String updatedNote) {
        mNotesTable.updateNote(noteId, updatedNote);
    }
}
