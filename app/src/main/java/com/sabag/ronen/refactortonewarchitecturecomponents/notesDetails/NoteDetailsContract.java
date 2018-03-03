package com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails;

public interface NoteDetailsContract {
    interface View {
        void showNote(String note);
        void close();
    }

    interface UserActions {
        void deleteNoteClick(long noteId);
        void updateNoteClick(long noteId, String updatedNote);
    }
}