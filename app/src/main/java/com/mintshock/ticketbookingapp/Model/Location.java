package com.mintshock.ticketbookingapp.Model;

import androidx.annotation.NonNull;

public class Location {
    private int Id;
    private String Name;

    public Location() {
    }

    @NonNull
    @Override
    public String toString() {
        return Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
