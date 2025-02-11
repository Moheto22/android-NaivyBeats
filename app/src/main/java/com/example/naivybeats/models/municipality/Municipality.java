package com.example.naivybeats.models.municipality;

public class Municipality
{
    int municipalityId;
    String name;
    int cityId;

    public Municipality()
    {
    }

    public Municipality(int municipalityId, String name, int cityId)
    {
        this.municipalityId = municipalityId;
        this.name = name;
        this.cityId = cityId;
    }

    public int getMunicipalityId()
    {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId)
    {
        this.municipalityId = municipalityId;
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
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }
}
