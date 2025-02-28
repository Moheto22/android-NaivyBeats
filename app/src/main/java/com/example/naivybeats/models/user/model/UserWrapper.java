package com.example.naivybeats.models.user.model;

import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper
{
    @SerializedName("$values")
    private List<Users> users = new ArrayList<>();

    public List<Users> getUsers() {
        return users;
    }
}
