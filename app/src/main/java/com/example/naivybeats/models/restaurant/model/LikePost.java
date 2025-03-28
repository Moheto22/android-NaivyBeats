package com.example.naivybeats.models.restaurant.model;

import java.sql.Date;

public class LikePost
{
    Date likeDate;

    public LikePost()
    {
    }

    public LikePost(Date likeDate)
    {
        this.likeDate = likeDate;
    }

    public Date getLikeDate()
    {
        return likeDate;
    }

    public void setLikeDate(Date likeDate)
    {
        this.likeDate = likeDate;
    }
}
