package com.jmp2017w.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an user.
 */
public class User
{
    private String login;
    private Set<String> watchedMovies = new HashSet<String>();

    /**
     * Default constructor.
     */
    public User()
    {
        // nothing
    }

    /**
     * Constructor with parameters.
     *
     * @param login login of user
     */
    public User(String login)
    {
        this.login = login;
    }

    /**
     * Gets login.
     *
     * @return login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Sets new login.
     *
     * @param login new login.
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * Gets watched by user list of movies.
     *
     * @return list of movies
     */
    public Set<String> getWatchedMovies()
    {
        return watchedMovies;
    }

    /**
     * Sets new list of watched by user movies.
     *
     * @param watchedMovies new watched movies list.
     */
    public void setWatchedMovies(Set<String> watchedMovies)
    {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Adds movie to watched list.
     *
     * @param watchedMovie watched movie name.
     */
    public void addWatchedMovie(String watchedMovie)
    {
        watchedMovies.add(watchedMovie);
    }
}