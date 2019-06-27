package com.chuhieu.note_sql.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
//    create table
    public static final String Note_SQL = " CREATE TABLE "+TABLE_NAME+"("+
             COMLUM_ID      +" INTEGER PRIMARY KEY AUTOINCREMENT," +
             COMLUM_TITLE   +" TEXT," +
             COMLUM_CONTENT +" TEXT)";

//cap quyen`
    public NOTE_DAO (Context context){
        databasehelper = new Database(context);
        db = databasehelper.getWritableDatabase();
    }
//    them du lieu
    public static int insertNote(Note note){
        ContentValues values = new ContentValues();
        values.put(COMLUM_TITLE,note.getTitle());
        values.put(COMLUM_CONTENT,note.getContent());

        try {
            if (db.insert(TABLE_NAME,null,values)<0){

                return -1;
            }

        }catch (Exception ex){
            Log.e("Notedao",ex.getMessage());
        }

    return 1;
    }
//    lay du lieu nguoi dung hien thi len rececyl view
    public List<Note> getallNguoiDung(){
        List <Note> ls = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Note  note = new Note();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));

            cursor.moveToNext();
            ls.add(note);

        }
        cursor.close();
        return ls;


    }
//    delete 1 hang dua tren username
    public int deleteNguoiDungByID(String id) {
        int result = db.delete(TABLE_NAME, COMLUM_ID+"=?", new String[]{id});
        if (result == 0){
            return -1;
        }

        return 1;
    }

}
