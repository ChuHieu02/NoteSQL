package com.chuhieu.note_sql.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chuhieu.note_sql.DAO.NOTE_DAO;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Note.SQL";
    public static final int VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NOTE_DAO.Note_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NOTE_DAO.TABLE_NAME);

    }
}
