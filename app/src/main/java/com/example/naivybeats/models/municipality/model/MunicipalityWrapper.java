package com.example.naivybeats.models.municipality.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MunicipalityWrapper
{
    @SerializedName("$values")
    private List<Municipality> provinces = new ArrayList<>();

    public List<Municipality> getProvinces() {
        return provinces;
    }
}
