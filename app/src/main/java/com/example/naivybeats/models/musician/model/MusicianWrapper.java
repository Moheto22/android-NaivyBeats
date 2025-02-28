package com.example.naivybeats.models.musician.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MusicianWrapper
{
    @SerializedName("$values")
    private List<Musician> musicians = new ArrayList<>();

    public List<Musician> getMusicians() {
        return musicians;
    }
}
