package com.sabag.ronen.refactortonewarchitecturecomponents.storage;

public class NoteData {

    private long mId;
    private String mNote;

    public NoteData(String note) {
        mNote = note;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getNote() {
        return mNote;
    }
}