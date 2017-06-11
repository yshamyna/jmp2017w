package com.jmp2017w.thread;

/**
 *
 */
public class Incrementor implements Runnable
{
    private long value;
    private long howManyTimesToIncrement;

    public Incrementor(long value, long howManyTimesToIncrement)
    {
        this.value = value;
        this.howManyTimesToIncrement = howManyTimesToIncrement;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " has been started.");
        if (howManyTimesToIncrement > 0)
        {
            for (long i = 0; i < howManyTimesToIncrement; i++)
            {
                value++;
            }
        }
        else
        {
            System.out.println("Unable to increment " + howManyTimesToIncrement + " time(-s).");
        }
        System.out.println(Thread.currentThread().getName() + " finished calculations.");
    }

    public long getValue()
    {
        return value;
    }
}