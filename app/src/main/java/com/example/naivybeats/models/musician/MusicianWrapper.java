package com.example.naivybeats.models.time.model;

import com.example.naivybeats.models.musician.Musician;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MusicianWrapper
{
    @SerializedName("$values")
    private List<Musician> musicians;

    public List<Musician> getMusicians() {
        return musicians != null ? musicians : new ArrayList<>();
    }
}
