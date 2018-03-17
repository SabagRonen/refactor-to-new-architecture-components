package com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails;

import com.sabag.ronen.refactortonewarchitecturecomponents.storage.INotesTable;
import com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl.NoteData;

class NoteDetailsModel {

    private final INotesTable mNotesTable;

    NoteDetailsModel(INotesTable notesTable) {
        mNotesTable = notesTable;
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
