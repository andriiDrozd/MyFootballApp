package com.example.footballapp.util;

public enum Region {
    GERMANY(48);

    private int id;

    Region(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
