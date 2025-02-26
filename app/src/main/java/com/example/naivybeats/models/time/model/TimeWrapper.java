package com.example.naivybeats.models.time.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TimeWrapper
{
    @SerializedName("$values")
    private List<Time> times = new ArrayList<>();

    public List<Time> getTimes() {
        return times;
    }
}
