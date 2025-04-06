package com.example.naivybeats.models.post;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.sql.Date;

public class PostDTO
{
    @SerializedName("publication_id")
    private int postId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("publication_date")
    private Date postDate;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("multimedia_content")
    private File multimedia;

    public PostDTO()
    {
    }

    public PostDTO(int postId, int userId, Date postDate, String description, File multimedia, String title)
    {
        this.postId = postId;
        this.userId = userId;
        this.postDate = postDate;
        this.description = description;
        this.multimedia = multimedia;
        this.title = title;
    }

    public int getPostId()
    {
        return postId;
    }

    public String getTitle()
    {
        return title;
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

    public File getMultimedia()
    {
        return multimedia;
    }

    public void setMultimedia(File multimedia)
    {
        this.multimedia = multimedia;
    }
}

