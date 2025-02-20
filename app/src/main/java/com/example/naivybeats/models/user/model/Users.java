package com.example.naivybeats.models.user.model;

import java.sql.Date;

public abstract class Users
{
    int userId;
    String name;
    String photo;
    String email;
    String password;
    int phoneNumber;
    Date creationDate;
    Date editionDate;
    int municipalityId;

    public Users()
    {
    }

    public Users(int userId, String name, String photo, String email, String password, int phoneNumber, Date creationDate, Date editionDate, int municipalityId)
    {
        this.userId = userId;
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.editionDate = editionDate;
        this.municipalityId = municipalityId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
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
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getEditionDate()
    {
        return editionDate;
    }

    public void setEditionDate(Date editionDate)
    {
        this.editionDate = editionDate;
    }

    public int getMunicipalityId()
    {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId)
    {
        this.municipalityId = municipalityId;
    }
}
