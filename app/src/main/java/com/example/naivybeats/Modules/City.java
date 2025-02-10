package com.example.naivybeats.Modules;

public class City
{
    int cityId;
    String name;

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
}
