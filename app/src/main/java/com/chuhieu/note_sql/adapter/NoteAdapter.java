package com.chuhieu.note_sql.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chuhieu.note_sql.DAO.NOTE_DAO;
import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.Onclick;
import com.chuhieu.note_sql.R;
import com.chuhieu.note_sql.SuaGhiChu;
import com.chuhieu.note_sql.ThemGhiChu;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> noteList;
    private Context context;
    private LayoutInflater layoutInflater;
    private NOTE_DAO noteDao;
    private Onclick onclick;
    public static byte[] url;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.noteDao = noteDao;
        noteDao = new NOTE_DAO(context);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_note, parent, false);
        return new NoteHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, final int position) {
        final Note note = noteList.get(position);
        holder.tvtitle.setText(note.getTitle());
        holder.tvcontent.setText(note.getContent());
        holder.tvdate.setText(formatDate(note.getDate()));
        holder.dot.setText(Html.fromHtml("&#8226;"));
        url = note.getImage();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.Onclickitem(noteList.get(position));

                Intent intent = new Intent(context, SuaGhiChu.class);

                intent.putExtra("image",note.getImage());
                intent.putExtra("title",note.getTitle());
                intent.putExtra("content",note.getContent());

                context.startActivity(intent);
            }
        });
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("d MMMM");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tvtitle, tvcontent, tvdate, dot;
        CardView cardView;
//        ImageView imgdel;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tvtitle = itemView.findViewById(R.id.tv_title);
            tvcontent = itemView.findViewById(R.id.tv_content);
            tvdate = itemView.findViewById(R.id.tvdate);
            dot = itemView.findViewById(R.id.dot);
//            imgdel = itemView.findViewById(R.id.imgdel);


        }
    }

    public void update(List<Note> notes) {
        this.noteList.addAll(notes);
        notifyDataSetChanged();
    }

    public void Clearall() {
        this.noteList.clear();
        notifyDataSetChanged();
    }


}
