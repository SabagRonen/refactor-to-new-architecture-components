package com.sabag.ronen.refactortonewarchitecturecomponents.storage.notesTableImpl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class NotesTableSqlHelper extends SQLiteOpenHelper {

    NotesTableSqlHelper(Context context) {
        super(context, NotesTable.TABLE_NAME, null, NotesTable.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NotesTable.TABLE_NAME +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY," +
                NotesTable.COLUMN_NAME_NOTE + NotesTable.TEXT_TYPE + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}