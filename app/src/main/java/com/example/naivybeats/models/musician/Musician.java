package com.example.naivybeats.models.musician;

import com.example.naivybeats.models.style.StyleWrapper;
import com.example.naivybeats.models.time.model.TimeWrapper;
import com.example.naivybeats.models.user.model.Users;
import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Musician extends Users {
    private StyleWrapper styleWrapper;
    @SerializedName("Times")
    private TimeWrapper timeWrapper;
    private List<Musician> musicians;

    public Musician() {}

    public List<Time> getTimes() {
        return (timeWrapper != null) ? timeWrapper.getTimes() : new ArrayList<>();
    }

    public void setTimeWrapper(TimeWrapper timeWrapper) {
        this.timeWrapper = timeWrapper;
    }

    public List<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Musician> musicians) {
        this.musicians = musicians;
    }
}
