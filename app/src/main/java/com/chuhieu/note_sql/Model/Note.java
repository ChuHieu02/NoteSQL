package com.chuhieu.note_sql.Model;

public class Note {

    String title,content,date;
    int id;
    private byte[] image;

    public Note(String title, String content, String date, int id, byte[] image) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.id = id;
        this.image = image;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
