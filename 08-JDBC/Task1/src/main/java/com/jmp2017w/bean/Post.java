package com.jmp2017w.bean;

import java.sql.Timestamp;

/**
 * Represents a post in 'posts' table in DB.
 */
public class Post
{
    private Long id;
    private Long userId;
    private String text;
    private Timestamp addedDate;

    public Post()
    {
    }

    public Post(Long id, Long userId, String text, Timestamp addedDate)
    {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.addedDate = addedDate;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Timestamp getAddedDate()
    {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate)
    {
        this.addedDate = addedDate;
    }
}