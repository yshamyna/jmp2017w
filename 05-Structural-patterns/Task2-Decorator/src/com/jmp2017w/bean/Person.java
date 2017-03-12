package com.jmp2017w.bean;

/**
 * Describes a person.
 */
public class Person
{
    private String name;

    /**
     * Creates a person.
     * @param name name of the person
     * @throws IllegalArgumentException if argument <tt>name</tt> is blank.
     */
    public Person(String name)
    {
        if (name == null || "".equals(name.trim()))
        {
            throw new IllegalArgumentException("Name of person cannot be blank");
        }
        this.name = name;
    }

    /**
     * Gets name of the person
     * @return {@link String} person name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Converts Person object to a string.
     * @return person as string
     */
    public String toString()
    {
        return name;
    }
}