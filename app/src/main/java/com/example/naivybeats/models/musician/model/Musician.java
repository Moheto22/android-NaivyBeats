package com.example.naivybeats.models.musician.model;

import com.example.naivybeats.models.style.Style;
import com.example.naivybeats.models.style.StyleWrapper;
import com.example.naivybeats.models.time.model.TimeWrapper;
import com.example.naivybeats.models.user.model.Users;
import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Musician extends Users {
    private StyleWrapper styleWrapper;
    @SerializedName("Times")
    private TimeWrapper timeWrapper;

    public Musician() {
        super();
    }

    public List<Time> getTimes() {
        return timeWrapper.getTimes();
    }

    public List<Style> getStyles(){
        return styleWrapper.getStyles();
    }
}
