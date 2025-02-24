package com.example.naivybeats.models.musician;

import com.example.naivybeats.models.style.Style;
import com.example.naivybeats.models.user.model.Users;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Musician extends Users
{
    List<Style> styles;

    public Musician()
    {
    }

    public Musician(int userId, String name, String photo, Double longitud, Double latitud, int municipalityId, Date editionDate, Date creationDate, int phoneNumber, String password, String email, List<Style> styles)
    {
        super(userId, name, photo, longitud, latitud, municipalityId, editionDate, creationDate, phoneNumber, password, email);
        this.styles = styles;
    }

    public List<Style> getStyles()
    {
        return styles;
    }

    public void setStyles(List<Style> styles)
    {
        this.styles = styles;
    }
}
