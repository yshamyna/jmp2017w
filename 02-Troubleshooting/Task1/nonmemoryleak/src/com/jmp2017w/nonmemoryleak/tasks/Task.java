package com.jmp2017w.nonmemoryleak.tasks;

import java.io.IOException;

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
    }
}