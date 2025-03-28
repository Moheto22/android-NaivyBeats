package com.example.naivybeats.models.style.model;

import com.example.naivybeats.models.musician.model.Musician;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Style
{
    @SerializedName("style_id")
    private int styleId;
    private String name;
    @SerializedName("Musician")
    private transient List<Musician> musicians;

    public Style() {}

    public Style(int styleId, String name, List<Musician> musicians)
    {
        this.styleId = styleId;
        this.name = name;
        this.musicians = musicians;
    }

    public int getStyleId()
    {
        return styleId;
    }

    public void setStyleId(int styleId)
    {
        this.styleId = styleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
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
