package com.example.naivybeats.models.restaurant;

import com.example.naivybeats.models.user.Users;

import java.sql.Date;
import java.sql.Time;

public class Restaurant extends Users
{
    String direction;
    Time openingTime;
    Time closingTime;

    public Restaurant()
    {
    }

    public Restaurant(int userId, String name, String photo, String email, String password, int phoneNumber, Date creationDate, Date editionDate, int municipalityId, String direction, Time openingTime, Time closingTime)
    {
        super(userId, name, photo, email, password, phoneNumber, creationDate, editionDate, municipalityId);
        this.direction = direction;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public Time getClosingTime()
    {
        return closingTime;
    }

    public void setClosingTime(Time closingTime)
    {
        this.closingTime = closingTime;
    }

    public Time getOpeningTime()
    {
        return openingTime;
    }

    public void setOpeningTime(Time openingTime)
    {
        this.openingTime = openingTime;
    }
}
