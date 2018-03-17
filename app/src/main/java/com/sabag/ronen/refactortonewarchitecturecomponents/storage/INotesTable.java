package com.sabag.ronen.refactortonewarchitecturecomponents.storage;

import com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl.NoteData;

import java.util.List;

public interface INotesTable {
    void add(NoteData noteData);
    NoteData get(long noteId);
    List<NoteData> getAll();
    void delete(long noteId);
    void updateNote(long noteId, String note);
}
