package com.jmp2017w.nonmemoryleak.tasks;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class prints frequency word book.
 */
public class FrequencyWordBookTask extends Task
{
    private Map<String, Integer> frequencyWordBook;

    /**
     * Reads zipped file and prints information about how many times each word occurs in the file.
     * @param zipFilename name of zipped file
     * @throws IOException in case of any I/O errors
     * @throws IllegalStateException if zipped file name is empty or NULL
     */
    public void execute(String zipFilename) throws IOException
    {
        super.execute(zipFilename);
        collectFrequencyData(zipFilename);
        printFrequencyData();
    }

    /**
     * Reads zipped file and collects information about how many times each word occurs in the file.
     * @param zipFilename name of zipped file
     * @throws IOException in case of any I/O errors
     */
    private void collectFrequencyData(String zipFilename) throws IOException
    {
        frequencyWordBook = new HashMap<String, Integer>();
        ZipInputStream zipInputStream = null;
        ZipEntry zipEntry = null;
        try
        {
            ClassLoader classLoader = getClass().getClassLoader();
            zipInputStream = new ZipInputStream(classLoader.getResourceAsStream(zipFilename));
            zipEntry = zipInputStream.getNextEntry();
            if (zipEntry != null)
            {
                readFile(zipInputStream);
            }
        }
        finally
        {
            if (zipInputStream != null)
            {
                if (zipEntry != null)
                {
                    zipInputStream.closeEntry();
                }
                zipInputStream.close();
            }
        }
    }

    /**
     * Reads file.
     * @param zipInputStream input stream of zipped file
     * @throws IOException in case of any I/O errors
     */
    private void readFile(ZipInputStream zipInputStream) throws IOException
    {
        Scanner scanner = new Scanner(zipInputStream);
        while (scanner.hasNextLine())
        {
            performActionWithLine(scanner.nextLine());
        }
    }

    /**
     * Splits line by space and counts how many times each word occurs.
     * @param line string line
     */
    private void performActionWithLine(String line)
    {
        String[] words = line.trim().split(" ");
        Integer count;
        for (String word : words)
        {
            count = frequencyWordBook.get(word);
            count = count == null ? 1 : count + 1;
            frequencyWordBook.put(word, count);
        }
    }

    /**
     * Prints frequency data.
     */
    private void printFrequencyData()
    {
        if (frequencyWordBook.isEmpty())
        {
            System.out.println("The file does not contain words.");
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