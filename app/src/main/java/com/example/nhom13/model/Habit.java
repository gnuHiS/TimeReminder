package com.example.nhom13.model;

import java.io.Serializable;

public class Habit implements Serializable {
    int id;
    String name;
    int repeat;
    String time;

    public Habit(int id, String name, int repeat, String time) {
        this.id = id;
        this.name = name;
        this.repeat = repeat;
        this.time = time;
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

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
