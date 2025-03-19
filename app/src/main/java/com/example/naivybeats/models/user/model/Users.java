package com.example.naivybeats.models.user.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class Users implements Serializable
{
    int user_id;
    String name;
    String photo;
    String email;
    String password;
    int phone_number;
    Date creation_date;
    Date edition_date;
    Date deleted_at;
    @SerializedName("municipality_id")
    int province_id;
    Double latitud;
    Double longitud;

    public Users()
    {
    }

    public Users(int user_id, String name, String photo, String email, String password, int phone_number, Date creation_date, Date edition_date, Date deleted_at, int province_id, Double latitud, Double longitud)
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
    }

    public int getUserId()
    {
        return user_id;
    }

    public void setUserId(int userId)
    {
        this.user_id = userId;
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

    public int getPhoneNumber()
    {
        return phone_number;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phone_number = phoneNumber;
    }

    public Date getCreationDate()
    {
        return creation_date;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creation_date = creationDate;
    }

    public Date getEditionDate()
    {
        return edition_date;
    }

    public void setEditionDate(Date editionDate)
    {
        this.edition_date = editionDate;
    }

    public int getMunicipalityId()
    {
        return province_id;
    }

    public void setMunicipalityId(int municipalityId)
    {
        this.province_id = municipalityId;
    }

    public Double getLatitud()
    {
        return latitud;
    }

    public void setLatitud(Double latitud)
    {
        this.latitud = latitud;
    }

    public Double getLongitud()
    {
        return longitud;
    }

    public void setLongitud(Double longitud)
    {
        this.longitud = longitud;
    }

    public Date getDeleted_at()
    {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at)
    {
        this.deleted_at = deleted_at;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(int phone_number)
    {
        this.phone_number = phone_number;
    }

    public Date getCreation_date()
    {
        return creation_date;
    }

    public void setCreation_date(Date creation_date)
    {
        this.creation_date = creation_date;
    }

    public Date getEdition_date()
    {
        return edition_date;
    }

    public void setEdition_date(Date edition_date)
    {
        this.edition_date = edition_date;
    }

    public int getProvince_id()
    {
        return province_id;
    }

    public void setProvince_id(int province_id)
    {
        this.province_id = province_id;
    }
}
