package com.jmp2017w.memoryleak.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class prints frequency word book.
 */
public class FrequencyWordBookTask extends Task
{
    private Map<String, Integer> frequencyWordBook;
    private List<String> stringHolder;

    /**
     * Reads zipped file and prints information about how many times each word occurs in the file.
     * @param zipFilename name of zipped file
     * @throws IOException in case of any I/O errors
     * @throws IllegalStateException if zipped file name is empty or NULL
     */
    public void execute(String zipFilename) throws IOException
    {
        stringHolder = new ArrayList<String>();
        super.execute(zipFilename);
        collectFrequencyData();
        printFrequencyData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performActionWithLine(String line)
    {
        String[] words = line.trim().split(" ");
        for (String word : words)
        {
            stringHolder.add(word);
        }
    }

    /**
     * Collects frequency data from words.
     */
    private void collectFrequencyData()
    {
        frequencyWordBook = new HashMap<String, Integer>();
        for (String string : stringHolder)
        {
            Integer count = frequencyWordBook.get(string);
            count = count == null ? Integer.valueOf(1) : count + 1;
            frequencyWordBook.put(string, count);
        }
    }

    /**
     * Prints frequency data.
     */
    private void printFrequencyData()
    {
        if (frequencyWordBook.isEmpty())
        {
            System.out.println("The file does not contain any words.");
        }
        else
        {
            for (String word : frequencyWordBook.keySet())
            {
                System.out.print("Word: " + word + ", frequency: " + frequencyWordBook.get(word));
            }
        }
    }
}