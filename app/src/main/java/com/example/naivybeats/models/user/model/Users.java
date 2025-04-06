package com.example.naivybeats.models.user.model;

import com.example.naivybeats.models.style.model.Style;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Users implements Serializable
{
    private int user_id;
    private String name;
    private String photo;
    private String email;
    private String password;
    private String phone_number;
    private String creation_date;
    private String edition_date;
    private String deleted_at;
    @SerializedName("municipality_id")
    private int province_id;
    private String latitud;
    private String longitud;
    private String description;

    public Users()
    {
    }

    public Users(int user_id, String name, String photo, String email, String password, String phone_number, String creation_date, String edition_date, String deleted_at, int province_id, String latitud, String longitud,String description)
    {
        this.user_id = user_id;
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.creation_date = creation_date;
        this.edition_date = edition_date;
        this.deleted_at = deleted_at;
        this.province_id = province_id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.description = description;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getCreation_date()
    {
        return creation_date;
    }

    public void setCreation_date(String creation_date)
    {
        this.creation_date = creation_date;
    }

    public String getEdition_date()
    {
        return edition_date;
    }

    public void setEdition_date(String edition_date)
    {
        this.edition_date = edition_date;
    }

    public String getDeleted_at()
    {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at)
    {
        this.deleted_at = deleted_at;
    }

    public int getProvince_id()
    {
        return province_id;
    }

    public void setProvince_id(int province_id)
    {
        this.province_id = province_id;
    }

    public String getLatitud()
    {
        return latitud;
    }

    public void setLatitud(String latitud)
    {
        this.latitud = latitud;
    }

    public String getLongitud()
    {
        return longitud;
    }

    public void setLongitud(String longitud)
    {
        this.longitud = longitud;
    }
}
