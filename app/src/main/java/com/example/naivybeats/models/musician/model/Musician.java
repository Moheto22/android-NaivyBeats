package com.example.naivybeats.models.musician.model;

import com.example.naivybeats.models.post.model.Post;
import com.example.naivybeats.models.style.model.Style;
import com.example.naivybeats.models.user.model.Users;
import com.example.naivybeats.models.time.model.Time;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Musician extends Users implements Serializable
{
    @SerializedName("Styles")
    List<Style> styles;
    @SerializedName("time")
    List<Time> times;
    @SerializedName("Publication")
    List<Post> posts;

    public Musician(int user_id, String name, String photo, String email, String password, String phone_number, String creation_date, String edition_date, String deleted_at, int province_id, String latitud, String longitud, String description, List<Style> styles, List<Time> times, List<Post> posts) {
        super(user_id, name, photo, email, password, phone_number, creation_date, edition_date, deleted_at, province_id, latitud, longitud, description);
        this.styles = styles;
        this.times = times;
        this.posts = posts;
    }

    public Musician() {
        super();
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
