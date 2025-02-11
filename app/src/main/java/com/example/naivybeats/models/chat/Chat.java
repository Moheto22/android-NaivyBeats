package com.example.naivybeats.models.chat;

import java.sql.Date;

public class Chat
{
    int chatId;
    Date creationDate;
    int restaurantId;
    int musicianId;

    public Chat()
    {
    }

    public Chat(int chatId, Date creationDate, int restaurantId, int musicianId)
    {
        this.chatId = chatId;
        this.creationDate = creationDate;
        this.restaurantId = restaurantId;
        this.musicianId = musicianId;
    }

    public int getChatId()
    {
        return chatId;
    }

    public void setChatId(int chatId)
    {
        this.chatId = chatId;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public int getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId)
    {
        this.restaurantId = restaurantId;
    }

    public int getMusicianId()
    {
        return musicianId;
    }

    public void setMusicianId(int musicianId)
    {
        this.musicianId = musicianId;
    }
}
