package com.example.naivybeats.models.municipality.model;

import com.example.naivybeats.models.province.models.City;
import com.example.naivybeats.models.user.model.UserWrapper;

import java.io.Serializable;
import java.util.List;

public class Municipality implements Serializable
{
    int municipality_id;
    String name;
    int city_id;

    MunicipalityWrapper municipalityWrapper;

    public Municipality()
    {
    }

    public Municipality(int municipality_id, String name, int city_id)
    {
        this.municipality_id = municipality_id;
        this.name = name;
        this.city_id = city_id;
    }

    public int getMunicipalityId()
    {
        return municipality_id;
    }

    public void setMunicipalityId(int municipalityId)
    {
        this.municipality_id = municipalityId;
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
    public List<Municipality> getMunicipalitis()
    {
        return municipalityWrapper.getMunicipalities();
    }
}
