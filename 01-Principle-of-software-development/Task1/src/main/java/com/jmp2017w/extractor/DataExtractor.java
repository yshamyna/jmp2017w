package com.jmp2017w.extractor;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Extracts certain data from a file and stores it in a list.
 */
public abstract class DataExtractor<T>
{
    protected List<T> data;

    /**
     * Extracts data from a file.
     * @param filename name of a file
     * @throws IllegalStateException if specified name of a file is empty or NULL
     */
    public List<T> extract(String filename)
    {
        if (StringUtils.isBlank(filename))
        {
            throw new IllegalStateException("Name of the file must be specified!");
        }
        else
        {
            data = new ArrayList<T>();
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filename);

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine())
            {
                parse(scanner.nextLine());
            }
            scanner.close();

            return data;
        }
    }

    /**
     * Parses line of a file.
     * @param line line of a file
     */
    protected abstract void parse(String line);
}
