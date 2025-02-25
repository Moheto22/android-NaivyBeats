package com.example.naivybeats.models.musician;

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
