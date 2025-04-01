package com.example.naivybeats.models.time.model;

import com.example.naivybeats.models.musician.model.Musician;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Time implements Serializable
{
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("Musician")
    private transient List<Musician> musicians;

    public Time(int id, String name, List<Musician> musicians)
    {
        this.id = id;
        this.name = name;
        this.musicians = musicians;
    }

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

    public List<Musician> getMusicians()
    {
        return musicians;
    }
    public void setMusicians(List<Musician> musicians)
    {
        this.musicians = musicians;
    }
}
