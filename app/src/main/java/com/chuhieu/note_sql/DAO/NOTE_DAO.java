package com.chuhieu.note_sql.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.database.Database;

import java.util.ArrayList;
import java.util.List;


public class NOTE_DAO {
    public static SQLiteDatabase db;
    public Database databasehelper;


    public static final String TABLE_NAME = "Note";
    public static final String COMLUM_ID = "id";
    public static final String COMLUM_TITLE = "title";
    public static final String COMLUM_CONTENT = "content";
    public static final String COMLUM_DATE = "date";
    public static final String COMLUM_IMAGE = "image";
    //    create table
    public static final String Note_SQL = " CREATE TABLE " + TABLE_NAME + "(" +
            COMLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COMLUM_TITLE + " TEXT," +
            COMLUM_CONTENT + " TEXT," +
            COMLUM_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
            COMLUM_IMAGE + " BLOB " +
            ")";

    //cap quyen`
    public NOTE_DAO(Context context) {
        databasehelper = new Database(context);
        db = databasehelper.getWritableDatabase();
    }

    public int InsertAnh(String title, String content, byte[] hinh)    {
        ContentValues values = new ContentValues();
        values.put(COMLUM_TITLE, title);
        values.put(COMLUM_CONTENT, content);
        values.put(COMLUM_IMAGE, hinh);
        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {

                return -1;
            }

        } catch (Exception ex) {
            Log.e("Notedao", ex.getMessage());
        }

        return 1;
    }

//        them du lieu
    public static int insertNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(COMLUM_TITLE, note.getTitle());
        values.put(COMLUM_CONTENT, note.getContent());
        values.put(COMLUM_IMAGE, note.getImage());

        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {

                return -1;
            }

        } catch (Exception ex) {
            Log.e("Notedao", ex.getMessage());
        }

        return 1;
    }



    //    lay du lieu nguoi dung hien thi len rececyl view
    public List<Note> getallNguoiDung() {
        List<Note> ls = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setDate(cursor.getString(3));
            note.setImage(cursor.getBlob(4));

            cursor.moveToNext();
            ls.add(note);

        }
        cursor.close();
        return ls;

    }


    //    delete 1 hang dua tren username
    public int deleteNguoiDungByID(String id) {
        int result = db.delete(TABLE_NAME, COMLUM_ID + " = ?", new String[]{id});
        if (result == 0) {
            return -1;
        }

        return 1;
    }

    public boolean Update(String Id, String title, String content) {

        ContentValues values = new ContentValues();
        values.put(COMLUM_TITLE, title);
        values.put(COMLUM_ID, Id);
        values.put(COMLUM_CONTENT, content);

        db.update(TABLE_NAME, values, "Id=?", new String[]{Id});
        return true;
    }

}
