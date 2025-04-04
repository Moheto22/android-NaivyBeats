package com.example.naivybeats.models.restaurant.model;

import com.example.naivybeats.models.user.model.Users;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Restaurant extends Users
{
    String openingTime;
    String closingTime;

    public Restaurant()
    {
    }

    public Restaurant(int user_id, String name, String photo, String email, String password, int phone_number, String creation_date, String edition_date, String deleted_at, int province_id, BigDecimal latitud, BigDecimal longitud, String direction, String openingTime, String closingTime)
    {
        super(user_id, name, photo, email, password, phone_number, creation_date, edition_date, deleted_at, province_id, latitud, longitud);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getClosingTime()
    {
        return closingTime;
    }

    public void setClosingTime(String closingTime)
    {
        this.closingTime = closingTime;
    }

    public String getOpeningTime()
    {
        return openingTime;
    }

    public void setOpeningTime(String openingTime)
    {
        this.openingTime = openingTime;
    }
}
