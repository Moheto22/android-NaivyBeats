package com.example.naivybeats.models.user.model;

public class Rate
{
    int appraiserId;
    int valuedId;
    int puntuation;
    String comment;

    public Rate()
    {
    }

    public Rate(int appraiserId, int valuedId, int puntuation, String comment)
    {
        this.appraiserId = appraiserId;
        this.valuedId = valuedId;
        this.puntuation = puntuation;
        this.comment = comment;
    }

    public int getAppraiserId()
    {
        return appraiserId;
    }

    public void setAppraiserId(int appraiserId)
    {
        this.appraiserId = appraiserId;
    }

    public int getValuedId()
    {
        return valuedId;
    }

    public void setValuedId(int valuedId)
    {
        this.valuedId = valuedId;
    }

    public int getPuntuation()
    {
        return puntuation;
    }

    public void setPuntuation(int puntuation)
    {
        this.puntuation = puntuation;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
