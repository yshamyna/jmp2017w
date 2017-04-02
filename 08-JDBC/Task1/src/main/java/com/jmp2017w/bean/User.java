package com.jmp2017w.bean;

import java.sql.Timestamp;

/**
 * Represents an user in 'users' table in DB.
 */
public class User
{
    private Long id;
    private String name;
    private String surname;
    private Timestamp birthdate;

    public User()
    {
    }

    public User(Long id, String name, String surname, Timestamp birthdate)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Timestamp getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate)
    {
        this.birthdate = birthdate;
    }
}