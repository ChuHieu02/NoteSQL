package com.chuhieu.note_sql;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chuhieu.note_sql.DAO.NOTE_DAO;
import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.adapter.NoteAdapter;
import com.chuhieu.note_sql.database.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private long backPresstime;
    private Toast backToast;
    NOTE_DAO note_dao;
    Database database;
    RecyclerView recyclerView;
    List<Note> noteList = new ArrayList<>();
    NoteAdapter noteAdapter;
    static int id;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rclist);
        note_dao = new NOTE_DAO(MainActivity.this);
        noteList = note_dao.getallNguoiDung();
        noteAdapter = new NoteAdapter(noteList, MainActivity.this);

        noteAdapter.setOnclick(new Onclick() {
            @Override
            public void Onclickitem(Note note) {
//                Toast.makeText(getApplicationContext(), note.getId()+"", Toast.LENGTH_SHORT).show();
                id = note.getId();
            }
        });
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);


        //floatActionbutton
        FloatingActionButton floatingActionButton = findViewById(R.id.flbtnadd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemGhiChu.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        startActivity(new Intent(getApplicationContext(), BaothucActivity.class));

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (backPresstime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finish();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPresstime = System.currentTimeMillis();
    }

}

