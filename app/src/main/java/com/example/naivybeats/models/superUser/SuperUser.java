package com.example.naivybeats.models.superUser;

public class SuperUser
{
    int superUserId;
    String name;
    String email;
    String password;

    public SuperUser()
    {
    }

    public SuperUser(int superUserId, String password, String name, String email)
    {
        this.superUserId = superUserId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public int getSuperUserId()
    {
        return superUserId;
    }

    public void setSuperUserId(int superUserId)
    {
        this.superUserId = superUserId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
