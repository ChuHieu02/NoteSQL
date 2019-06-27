package com.chuhieu.note_sql.Model;

public class Note {

    String title,content;
    int id;

    public Note(String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note() {

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
