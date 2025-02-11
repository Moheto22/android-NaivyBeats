package com.example.naivybeats.models.superUser;

import java.sql.Date;

public class Ticket
{
    int incidentId;
    String description;
    String type;
    Date startDate;
    Date endDate;
    int userId;

    public Ticket()
    {
    }

    public Ticket(int incidentId, String description, String type, Date startDate, Date endDate, int userId)
    {
        this.incidentId = incidentId;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public int getIncidentId()
    {
        return incidentId;
    }

    public void setIncidentId(int incidentId)
    {
        this.incidentId = incidentId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
