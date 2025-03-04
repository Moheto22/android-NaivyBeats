package com.example.naivybeats.models.municipality.model;

import com.example.naivybeats.models.user.model.UserWrapper;
import com.example.naivybeats.models.user.model.Users;

import java.io.Serializable;

public class Province implements Serializable
{
    int province_id;
    String name;
    int city_id;
    UserWrapper userWrapper;

    public Province()
    {
    }

    public Province(int province_id, String name, int city_id)
    {
        this.province_id = province_id;
        this.name = name;
        this.city_id = city_id;
    }

    public int getMunicipalityId()
    {
        return province_id;
    }

    public void setMunicipalityId(int municipalityId)
    {
        this.province_id = municipalityId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCityId()
    {
        return city_id;
    }

    public void setCityId(int cityId)
    {
        this.city_id = cityId;
    }
}
