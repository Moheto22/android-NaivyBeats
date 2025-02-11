package com.example.naivybeats.models.musician;

public class MusicianStyle
{
    int styleId;
    String styleName;

    public MusicianStyle()
    {
    }

    public MusicianStyle(int styleId, String styleName)
    {
        this.styleId = styleId;
        this.styleName = styleName;
    }

    public int getStyleId()
    {
        return styleId;
    }

    public void setStyleId(int styleId)
    {
        this.styleId = styleId;
    }

    public String getStyleName()
    {
        return styleName;
    }

    public void setStyleName(String styleName)
    {
        this.styleName = styleName;
    }
}
