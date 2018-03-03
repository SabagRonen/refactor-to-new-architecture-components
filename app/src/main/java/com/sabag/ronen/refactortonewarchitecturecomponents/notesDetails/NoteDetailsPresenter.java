package com.sabag.ronen.refactortonewarchitecturecomponents.notesDetails;

import com.sabag.ronen.refactortonewarchitecturecomponents.storage.NoteData;

import java.lang.ref.WeakReference;

class NoteDetailsPresenter implements NoteDetailsContract.UserActions{

    private final WeakReference<NoteDetailsContract.View> mView;
    private final NoteDetailsModel mModel;

    NoteDetailsPresenter(NoteDetailsContract.View view, NoteDetailsModel model) {
        mView = new WeakReference<>(view);
        mModel = model;
    }

    void loadData(long noteId) {
        if (mView.get() == null) return;

        NoteData noteData = mModel.getNote(noteId);
        mView.get().showNote(noteData.getNote());
    }

    @Override
    public void deleteNoteClick(long noteId) {
        mModel.deleteNote(noteId);

        if (mView.get() == null) return;
        mView.get().close();
    }

    @Override
    public void updateNoteClick(long noteId, String updatedNote) {
        mModel.updateNote(noteId, updatedNote);

        if (mView.get() == null) return;
        mView.get().close();
    }
}