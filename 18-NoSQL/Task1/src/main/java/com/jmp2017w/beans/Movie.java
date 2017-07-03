package com.jmp2017w.beans;

/**
 * Represents a movie.
 */
public class Movie
{
    private String name;

    /**
     * Default constructor.
     */
    public Movie()
    {
        // nothing
    }

    /**
     * Constructor with parameters.
     *
     * @param name the name of movie.
     */
    public Movie(String name)
    {
        this.name = name;
    }

    /**
     * Gets movie name.
     *
     * @return name of the movie
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets new movie name.
     *
     * @param name new movie name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}