package com.example.naivybeats.models.time.model;

import com.example.naivybeats.models.musician.model.Musician;
import com.example.naivybeats.models.musician.model.MusicianWrapper;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Time {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("Musician")
    private MusicianWrapper musicianWrapper;

    public Time() {}

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
    public List<Musician> getMusicians() {
        return musicianWrapper.getMusicians();
    }

}
