package com.example.naivybeats.models.restaurant.model;

import com.example.naivybeats.models.user.model.Users;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Restaurant extends Users
{
    String direction;
    Time openingTime;
    Time closingTime;

    public Restaurant()
    {
    }

    public Restaurant(int user_id, String name, String photo, String email, String password, int phone_number, Date creation_date, Date edition_date, Date deleted_at, int province_id, Double latitud, Double longitud, String direction, Time openingTime, Time closingTime)
    {
        super(user_id, name, photo, email, password, phone_number, creation_date, edition_date, deleted_at, province_id, latitud, longitud);
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
