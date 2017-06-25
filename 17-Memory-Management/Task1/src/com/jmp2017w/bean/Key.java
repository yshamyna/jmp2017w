package com.jmp2017w.bean;

/**
 *
 */
public class Key
{
    private Long id;

    public Key(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

}