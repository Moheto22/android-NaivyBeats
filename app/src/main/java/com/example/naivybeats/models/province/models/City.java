package com.example.naivybeats.models.province.models;

import com.example.naivybeats.models.municipality.model.Municipality;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City
{
    @SerializedName("city_id")
    int cityId;
    String name;
    @SerializedName("Municipality")
    List<Municipality> municipalities;

    public City()
    {
    }

    public City(int cityId, String name, List<Municipality> municipalities)
    {
        this.cityId = cityId;
        this.name = name;
        this.municipalities = municipalities;
    }

    public List<Municipality> getMunicipalities()
    {
        return municipalities;
    }

    public void setMunicipalities(List<Municipality> municipalities)
    {
        this.municipalities = municipalities;
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
