package com.example.naivybeats.Modules;

import java.sql.Date;
import java.sql.Time;

public class Musician extends Users
{
    Time preferredTime;

    public Musician()
    {
    }

    public Musician(int userId, String name, String photo, String email, String password, int phoneNumber, Date creationDate, Date editionDate, int municipalityId, Time preferredTime)
    {
        super(userId, name, photo, email, password, phoneNumber, creationDate, editionDate, municipalityId);
        this.preferredTime = preferredTime;
    }

    public Time getPreferredTime()
    {
        return preferredTime;
    }

    public void setPreferredTime(Time preferredTime)
    {
        this.preferredTime = preferredTime;
    }

}
