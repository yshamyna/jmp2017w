package com.jmp2017w.thread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random <code>long</code> numbers and sums up them.
 */
public class RandomSumAccumulator implements Runnable
{
    private long value;
    private long howManyNumbersToGenerate;

    public RandomSumAccumulator(long howManyNumbersToGenerate)
    {
        this.howManyNumbersToGenerate = howManyNumbersToGenerate;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " has been started.");
        if (howManyNumbersToGenerate > 0)
        {
            for (int i = 0; i < howManyNumbersToGenerate; i++)
            {
                value += ThreadLocalRandom.current().nextLong();
            }
        }
        else
        {
            System.out.println("Unable to generate " + howManyNumbersToGenerate + " numbers.");
        }
        System.out.println(Thread.currentThread().getName() + " finished calculations.");
    }

    public long getValue()
    {
        return value;
    }
}