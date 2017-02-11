package com.jmp2017w;

import com.jmp2017w.extractor.DataExtractor;
import com.jmp2017w.extractor.NumericExtractor;
import com.jmp2017w.extractor.StringExtractor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Create modular project with using DRY principle. You could use any of build managers (ant, maven, gradle and etc.).
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        final String filename = "file.txt";
        try
        {
            DataExtractor numericDataExtractor = new NumericExtractor();
            extractAndDisplayDataOnConsoleView("List of numeric data:", numericDataExtractor, filename);

            DataExtractor stringDataExtractor = new StringExtractor();
            extractAndDisplayDataOnConsoleView("List of string data:", stringDataExtractor, filename);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Extracts data and prints it on console view.
     * @param message head message
     * @param dataExtractor extractor
     * @param filename name of a file where data is taken from
     */
    private static void extractAndDisplayDataOnConsoleView(String message, DataExtractor dataExtractor, String filename)
    {
        if (dataExtractor == null || StringUtils.isBlank(filename))
        {
            System.out.println("Incorrect input data found.");
        }
        else
        {
            System.out.println(message);
            List<?> values = dataExtractor.extract(filename);
            if (values == null || values.isEmpty())
            {
                System.out.println("Nothing to show.");
            }
            else
            {
                for (Object value : values)
                {
                    System.out.println(value);
                }
            }
        }
    }
}
