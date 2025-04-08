package com.example.naivybeats.models.chat.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Chat
{
    @SerializedName("chat_id")
    Integer chatId;
    @SerializedName("creation_date")
    String creationDate;
    @SerializedName("restaurant_id")
    int restaurantId;
    @SerializedName("musician_id")
    int musicianId;

    public Chat() {}

    public Chat(Integer chatId, String creationDate, int restaurantId, int musicianId)
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

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String creationDate)
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
