package com.example.naivybeats.models.musician;

import java.sql.Date;

public class ApplyOffer
{
    Date applyDate;

    public ApplyOffer()
    {
    }

    public ApplyOffer(Date applyDate)
    {
        this.applyDate = applyDate;
    }

    public Date getApplyDate()
    {
        return applyDate;
    }

    public void setApplyDate(Date applyDate)
    {
        this.applyDate = applyDate;
    }
}
