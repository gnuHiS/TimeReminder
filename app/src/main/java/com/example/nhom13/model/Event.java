package com.example.nhom13.model;

import java.io.Serializable;

public class Event implements Serializable {
    int id;
    String name;
    String date;
    String time;
    int repeat;
    int setting;
    int important;

    public Event(int id, String name, String date, String time, int repeat, int setting, int important) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.setting = setting;
        this.important = important;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getSetting() {
        return setting;
    }

    public void setSetting(int setting) {
        this.setting = setting;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }
}
