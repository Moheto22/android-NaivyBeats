package com.example.naivybeats.models.musician.model;

import com.example.naivybeats.models.post.Post;
import com.example.naivybeats.models.style.model.Style;
import com.example.naivybeats.models.user.model.Users;
import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Musician extends Users implements Serializable
{
    Integer user_id;
    @SerializedName("Style")
    List<Style> styles;
    @SerializedName("time")
    List<Time> times;
    @SerializedName("Publication")
    List<Post> posts;

    public Musician(int user_id, String name, String photo, String email, String password, int phone_number, Date creation_date, Date edition_date, Date deleted_at, int province_id, Double latitud, Double longitud, int user_id1, List<Style> styles, List<Time> times, List<Post> posts)
    {
        super(user_id, name, photo, email, password, phone_number, creation_date, edition_date, deleted_at, province_id, latitud, longitud);
        this.user_id = null;
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
