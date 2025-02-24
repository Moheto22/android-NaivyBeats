package com.example.naivybeats.models.user.model;

import java.sql.Date;

public class Users
{
    int user_id;
    String name;
    String photo;
    String email;
    String password;
    int phone_number;
    Date creation_date;
    Date edition_date;
    int province_id;
    Double latitud;
    Double longitud;

    public Users()
    {
    }

    public Users(int userId, String name, String photo, Double longitud, Double latitud, int municipalityId, Date editionDate, Date creationDate, int phoneNumber, String password, String email)
    {
        this.user_id = userId;
        this.name = name;
        this.photo = photo;
        this.longitud = longitud;
        this.latitud = latitud;
        this.province_id = municipalityId;
        this.edition_date = editionDate;
        this.creation_date = creationDate;
        this.phone_number = phoneNumber;
        this.password = password;
        this.email = email;
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
}
