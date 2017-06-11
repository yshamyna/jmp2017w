package com.jmp2017w;

import com.jmp2017w.thread.Decrementor;
import com.jmp2017w.thread.Incrementor;
import com.jmp2017w.thread.RandomSumAccumulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Do 3 different calculations in parallel threads and print results in main thread.
 * Nice to have: do task using 2 approaches: classic  and using java.util.concurrency
 */
public class Runner
{

    public static void main(String[] args)
    {
        doClassicApproach();
        doConcurrencyApproach();
    }

    private static void doClassicApproach()
    {
        Decrementor decrementor = new Decrementor(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Thread decrementorThread = new Thread(decrementor);

        Incrementor incrementor = new Incrementor(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Thread incrementorThread = new Thread(incrementor);

        RandomSumAccumulator randomSumAccumulator = new RandomSumAccumulator(Integer.MAX_VALUE);
        Thread randomSumAccumulatorThread = new Thread(randomSumAccumulator);

        decrementorThread.start();
        incrementorThread.start();
        randomSumAccumulatorThread.start();

        try
        {
            decrementorThread.join();
            incrementorThread.join();
            randomSumAccumulatorThread.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("[Classic] Decrementor: " + decrementor.getValue());
        System.out.println("[Classic] Incrementor: " + incrementor.getValue());
        System.out.println("[Classic] RandomSumAccumulator: " + randomSumAccumulator.getValue());
    }

    private static void doConcurrencyApproach()
    {
        Decrementor decrementor = new Decrementor(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Incrementor incrementor = new Incrementor(Integer.MAX_VALUE, Integer.MAX_VALUE);
        RandomSumAccumulator randomSumAccumulator = new RandomSumAccumulator(Integer.MAX_VALUE);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(decrementor);
        executorService.execute(incrementor);
        executorService.execute(randomSumAccumulator);
        try
        {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("[Concurrency] Decrementor: " + decrementor.getValue());
        System.out.println("[Concurrency] Incrementor: " + incrementor.getValue());
        System.out.println("[Concurrency] RandomSumAccumulator: " + randomSumAccumulator.getValue());
    }
}