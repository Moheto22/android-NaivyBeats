package com.example.naivybeats.models.musician.model;

import com.example.naivybeats.models.post.Post;
import com.example.naivybeats.models.style.model.Style;
import com.example.naivybeats.models.user.model.Users;
import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Musician extends Users implements Serializable
{
    int user_id;
    @SerializedName("Style")
    List<Style> styles;
    @SerializedName("time")
    List<Time> times;
    @SerializedName("Publication")
    List<Post> posts;

    public Musician(int userId, String name, String photo, Double longitud, Double latitud, int municipalityId, Date editionDate, Date creationDate, int phoneNumber, String password, String email, int user_id, List<Style> styles, List<Time> times, List<Post> posts)
    {
        super(userId, name, photo, longitud, latitud, municipalityId, editionDate, creationDate, phoneNumber, password, email);
        this.user_id = user_id;
        this.styles = styles;
        this.times = times;
        this.posts = posts;
    }

    public Musician() {
        super();
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public List<Style> getStyles()
    {
        return styles;
    }

    public void setStyles(List<Style> styles)
    {
        this.styles = styles;
    }

    public List<Time> getTimes()
    {
        return times;
    }

    public void setTimes(List<Time> times)
    {
        this.times = times;
    }

    public List<Post> getPosts()
    {
        return posts;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts = posts;
    }
}
