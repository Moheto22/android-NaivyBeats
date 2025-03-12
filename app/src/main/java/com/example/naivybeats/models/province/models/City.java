package com.example.naivybeats.models.province.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City
{
    @SerializedName("city_id")
    int cityId;
    String name;
    CityWrapper citiWrapper;

    public City()
    {
    }

    public City(int cityId, String name)
    {
        this.cityId = cityId;
        this.name = name;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<City> getCities()
    {
        return citiWrapper.getCities();
    }
}
