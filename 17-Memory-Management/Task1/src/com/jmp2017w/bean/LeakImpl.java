package com.jmp2017w.bean;

/**
 *
 */
public class LeakImpl implements Leak
{
    private Leak leak;

    public LeakImpl(Leak leak)
    {
        this.leak = leak;
    }
}