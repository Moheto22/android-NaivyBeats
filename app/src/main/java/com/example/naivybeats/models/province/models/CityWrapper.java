package com.example.naivybeats.models.province.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityWrapper
{
    @SerializedName("$values")
    private List<City> cities = new ArrayList<>();

    public CityWrapper()
    {
    }

    public List<City> getCities()
    {
        return cities;
    }
}
