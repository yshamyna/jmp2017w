package com.jmp2017w.bean;

/**
 *
 */
public class SomeClassImpl implements SomeClass
{
    private static final long[] cache = new long[1024];

    private Leak leak;
    private long counter;

    public Leak leak()
    {
        return new LeakImpl(leak);
    }

    public long counter()
    {
        return counter;
    }

    public SomeClass copy(SomeClass someClass)
    {
        if (someClass != null)
        {
            counter = someClass.counter();
            leak = someClass.leak();
        }
        return this;
    }
}