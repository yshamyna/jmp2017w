package com.jmp2017w.thread;

/**
 *
 */
public class Decrementor implements Runnable
{
    private long value;
    private long howManyTimesToDecrement;

    public Decrementor(long value, long howManyTimesToDecrement)
    {
        this.value = value;
        this.howManyTimesToDecrement = howManyTimesToDecrement;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " has been started.");
        if (howManyTimesToDecrement > 0)
        {
            for (long i = 0; i < howManyTimesToDecrement; i++)
            {
                value--;
            }
        }
        else
        {
            System.out.println("Unable to decrement " + howManyTimesToDecrement + " time(-s).");
        }
        System.out.println(Thread.currentThread().getName() + " finished calculations.");
    }

    public long getValue()
    {
        return value;
    }
}