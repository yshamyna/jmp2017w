package com.jmp2017w.memoryleak.tasks;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Base class for executing tasks with files.
 */
public abstract class Task
{
    /**
     * Executes a task.
     * @param zipFilename zipped file to parse
     * @throws IOException in case of any I/O errors
     * @throws IllegalStateException if zipped file name is empty or NULL
     */
    public void execute(String zipFilename) throws IOException
    {
        if (zipFilename == null || zipFilename.trim().isEmpty())
        {
            throw new IllegalStateException("Incorrect input data found.");
        }

        ClassLoader classLoader = getClass().getClassLoader();
        ZipInputStream zipInputStream = new ZipInputStream(classLoader.getResourceAsStream(zipFilename));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        if (zipEntry != null)
        {
            readFile(zipInputStream);
        }
    }

    /**
     * Reads file.
     * @param zipInputStream input stream of zipped file
     * @throws IOException in case of any I/O errors
     */
    protected void readFile(ZipInputStream zipInputStream) throws IOException
    {
        Scanner scanner = new Scanner(zipInputStream);
        while (scanner.hasNextLine())
        {
            performActionWithLine(scanner.nextLine());
        }
    }

    /**
     * Parses line of a file.
     * @param line line of a file
     */
    protected abstract void performActionWithLine(String line);
}