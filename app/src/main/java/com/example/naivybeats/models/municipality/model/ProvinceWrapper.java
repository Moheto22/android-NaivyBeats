package com.example.naivybeats.models.municipality.model;

import com.example.naivybeats.models.musician.model.Musician;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProvinceWrapper
{
    @SerializedName("$values")
    private List<Province> provinces = new ArrayList<>();

    public List<Province> getProvinces() {
        return provinces;
    }
}
