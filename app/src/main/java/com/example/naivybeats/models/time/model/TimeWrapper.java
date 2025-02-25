package com.example.naivybeats.models.time.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TimeWrapper
{
    @SerializedName("$values")
    private List<Time> times;

    public List<Time> getTimes() {
        return times != null ? times : new ArrayList<>();
    }
}
