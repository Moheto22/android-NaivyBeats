package com.example.naivybeats.models.style;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StyleWrapper
{
    @SerializedName("$values")
    private List<Style> styles;

    public List<Style> getStyles() {
        return styles != null ? styles : new ArrayList<>();
    }
}
