package com.example.naivybeats.models.style;

public class Style
{
    int styleId;
    String name;

    public Style()
    {
    }

    public Style(String name, int styleId)
    {
        this.name = name;
        this.styleId = styleId;
    }

    public int getStyleId()
    {
        return styleId;
    }

    public void setStyleId(int styleId)
    {
        this.styleId = styleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
