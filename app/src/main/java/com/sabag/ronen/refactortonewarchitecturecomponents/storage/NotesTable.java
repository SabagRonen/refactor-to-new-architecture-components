package com.sabag.ronen.refactortonewarchitecturecomponents.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class NotesTable implements BaseColumns {

    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "NotesTable";
    static final String COLUMN_NAME_NOTE = "note";
    static final String TEXT_TYPE = " TEXT";

    private NotesTableSqlHelper mDbHelper;
    private SQLiteDatabase mDb;

    public NotesTable(Context context) {
        mDbHelper = new NotesTableSqlHelper(context);
    }

    public void open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void add(NoteData noteData) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NOTE, noteData.getNote());
        mDb.insert(TABLE_NAME, null, values);
    }

    public NoteData get(long noteId) {
        Cursor cursor = mDb.query(TABLE_NAME, null, _ID + " = " + noteId,
                null, null, null, null);
        cursor.moveToFirst();
        NoteData noteData = cursorToNoteData(cursor);
        cursor.close();
        return noteData;
    }

    public List<NoteData> getAll() {
        List<NoteData> result = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result.add(cursorToNoteData(cursor));
                cursor.moveToNext();
            }
        }
        return result;
    }

    public void delete(long noteId) {
        mDb.delete(TABLE_NAME, _ID + " = " + noteId, null);
    }

    public void updateNote(long noteId, String note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NOTE, note);
        mDb.update(TABLE_NAME, values, _ID + " = " + noteId, null);
    }

    private NoteData cursorToNoteData(Cursor theCursor) {
        long noteId = theCursor.getLong(0);
        String note = theCursor.getString(1);
        NoteData noteData = new NoteData(note);
        noteData.setId(noteId);
        return noteData;
    }
}