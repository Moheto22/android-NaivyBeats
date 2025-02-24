package com.example.naivybeats.models.time.model;

import com.example.naivybeats.models.musician.Musician;

import java.util.List;

public class Time
{
    int id;
    String name;
    List<Musician> musicians;

    public Time(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
