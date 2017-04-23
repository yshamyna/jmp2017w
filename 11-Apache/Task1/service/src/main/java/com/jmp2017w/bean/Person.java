package com.jmp2017w.bean;

import java.sql.Timestamp;

/**
 *
 */
public class Person
{
    private Long id;
    private String firstName;
    private String lastName;
    private Timestamp birthDate;
    private String hobbies;

    public Person()
    {
        super();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Timestamp getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthdate)
    {
        this.birthDate = birthdate;
    }

    public String getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(String hobbies)
    {
        this.hobbies = hobbies;
    }
}