package com.chuhieu.note_sql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chuhieu.note_sql.DAO.NOTE_DAO;
import com.chuhieu.note_sql.Model.Note;
import com.chuhieu.note_sql.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> noteList;
    private Context context;
    private LayoutInflater layoutInflater;
    private NOTE_DAO noteDao;

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.noteDao = noteDao;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_note,parent,false);
        return new NoteHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, final int position) {
        final  Note note = noteList.get(position);
        holder.tvtitle.setText(note.getTitle());
        holder.tvcontent.setText(note.getContent());
        holder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noteList.get(position);
                noteDao.deleteNguoiDungByID(noteList.get(position).getTitle());
                noteList.remove(note);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tvtitle,tvcontent;
        ImageView imgdel;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            tvtitle = itemView.findViewById(R.id.tv_title);
            tvcontent = itemView.findViewById(R.id.tv_content);
            imgdel = itemView.findViewById(R.id.imgdel);

        }
    }
}
