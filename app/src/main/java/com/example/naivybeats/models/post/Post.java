package com.example.naivybeats.models.post;

import java.sql.Date;

public class Post
{
    int postId;
    int userId;
    Date postDate;
    String description;
    String multimedia;

    public Post()
    {
    }

    public Post(int postId, int userId, Date postDate, String description, String multimedia)
    {
        this.postId = postId;
        this.userId = userId;
        this.postDate = postDate;
        this.description = description;
        this.multimedia = multimedia;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public Date getPostDate()
    {
        return postDate;
    }

    public void setPostDate(Date postDate)
    {
        this.postDate = postDate;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getMultimedia()
    {
        return multimedia;
    }

    public void setMultimedia(String multimedia)
    {
        this.multimedia = multimedia;
    }
}
