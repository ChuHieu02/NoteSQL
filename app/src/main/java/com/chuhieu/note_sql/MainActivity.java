package com.chuhieu.note_sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chuhieu.note_sql.DAO.NOTE_DAO;
import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.adapter.NoteAdapter;
import com.chuhieu.note_sql.database.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NOTE_DAO note_dao;
    Note note;
    Database database;

    RecyclerView recyclerView;
    List<Note> noteList = new ArrayList<>();
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushdata();

        //floatActionbutton
        FloatingActionButton floatingActionButton = findViewById(R.id.flbtnadd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertnote();
            }
        });
    }

    private void pushdata() {
        // dua du lieu len recyclerview
        recyclerView = findViewById(R.id.rclist);
        note_dao = new NOTE_DAO(this);
        noteList = note_dao.getallNguoiDung();
        noteAdapter = new NoteAdapter(noteList,MainActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);
    }

    private AlertDialog alertDialog;
    private void insertnote(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_dialog,null);
        builder.setView(dialog);

        TextView btnadd,btncancel;
        final EditText edttitle,edtcontennt;

        edttitle    = dialog.findViewById(R.id.edt_title);
        edtcontennt = dialog.findViewById(R.id.edt_content);
        btnadd      = dialog.findViewById(R.id.btnadd);
        btncancel   = dialog.findViewById(R.id.btncancel);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note_dao = new NOTE_DAO(MainActivity.this);
                note = new Note(null,edttitle.getText().toString(),edtcontennt.getText().toString());

                if (NOTE_DAO.insertNote(note)==1)
                {
                    Toast.makeText(getApplicationContext(),"Them Thanh Cong",
                            Toast.LENGTH_SHORT).show();
                    noteList.clear();
                    pushdata();

                    alertDialog.dismiss();


                }else {
                    Toast.makeText(getApplicationContext(),"Co loi xay ra",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        builder.create();
        alertDialog = builder.show();
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }
}

