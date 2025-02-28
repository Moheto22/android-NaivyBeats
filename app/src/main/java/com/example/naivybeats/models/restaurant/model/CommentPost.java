package com.example.naivybeats.models.restaurant.model;

import java.sql.Date;

public class CommentPost
{
    String content;
    Date publishDate;

    public CommentPost()
    {
    }

    public CommentPost(String content, Date publishDate)
    {
        this.content = content;
        this.publishDate = publishDate;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }
}
