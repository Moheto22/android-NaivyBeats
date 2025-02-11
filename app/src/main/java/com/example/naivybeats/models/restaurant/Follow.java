package com.example.naivybeats.models.restaurant;

public class Follow
{
    int musicianId;
    int restaurantId;

    public Follow()
    {
    }

    public Follow(int musicianId, int restaurantId)
    {
        this.musicianId = musicianId;
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

    public int getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId)
    {
        this.restaurantId = restaurantId;
    }
}
