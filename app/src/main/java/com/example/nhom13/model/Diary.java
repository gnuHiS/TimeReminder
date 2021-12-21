package com.example.nhom13.model;

public class Diary {
    int id;
    String title;
    String date;
    String contentMain;
    String content;

    public Diary(int id, String title, String date, String contentMain, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.contentMain = contentMain;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentMain() {
        return contentMain;
    }

    public void setContentMain(String contentMain) {
        this.contentMain = contentMain;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
