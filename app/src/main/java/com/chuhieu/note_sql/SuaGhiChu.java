package com.chuhieu.note_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuhieu.note_sql.DAO.NOTE_DAO;
import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.adapter.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class SuaGhiChu extends AppCompatActivity {
    private TextView btnadd, btncancel;
    private EditText edttitle, edtcontennt;
    NOTE_DAO note_dao;
    Note note;
    NoteAdapter noteAdapter;
    List<Note> noteList = new ArrayList<>();
    private ImageView editPhoto;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ghi_chu);

        edttitle = findViewById(R.id.tieudee);
        edtcontennt = findViewById(R.id.noidungg);
        btnadd = findViewById(R.id.btnadd);
        btncancel = findViewById(R.id.btncancel);
        editPhoto = (ImageView) findViewById(R.id.editPhoto);


        Intent intent2 = getIntent();
        if (intent2 != null) {

            edtcontennt.setText(intent2.getStringExtra("content"));
            edttitle.setText(intent2.getStringExtra("title"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(
                    intent2.getByteArrayExtra("image"), 0, intent2.getByteArrayExtra("image").length);
            editPhoto.setImageBitmap(bitmap);


            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    note_dao = new NOTE_DAO(getApplicationContext());

                    String title = edttitle.getText().toString();
                    String content = edtcontennt.getText().toString();
                    boolean isUdate = note_dao.Update(String.valueOf(MainActivity.id), title, content);
                    if (isUdate == true) {
                        Toast.makeText(SuaGhiChu.this, "Đã sửa ghi chú", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    } else {
                        Toast.makeText(SuaGhiChu.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }
            });

        }
    }


    public void arrowSua(View view) {
        onBackPressed();

    }

    private void dialog() {
        AlertDialog alertDialog;
    }

    public void delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                note_dao = new NOTE_DAO(getApplicationContext());
                note_dao.deleteNguoiDungByID(String.valueOf(MainActivity.id));
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(SuaGhiChu.this, "Đã xóa ghi chú", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        builder.create();
        builder.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
