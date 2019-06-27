package com.chuhieu.note_sql.Model;

public class Note {

    String Id,title,content;

    public Note(String id, String title, String content) {
        Id = id;
        this.title = title;
        this.content = content;
    }

    public Note() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
