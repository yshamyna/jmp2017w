package com.jmp2017w.nonmemoryleak.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is responsible for copying data.
 */
public class CopyTask extends Task
{
    /**
     * Copies zipped file. New file will have name <tt>copy.zip</tt>.
     * @param zipFilename name of zipped file
     * @throws IOException in case of any I/O errors
     * @throws IllegalStateException if zipped file name is empty or NULL
     */
    @Override
    public void execute(String zipFilename) throws IOException
    {
        super.execute(zipFilename);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try
        {
            ClassLoader classLoader = getClass().getClassLoader();
            inputStream = classLoader.getResourceAsStream(zipFilename);

            File copy = new File("copy.zip");
            outputStream = new FileOutputStream(copy.getName());

            int data = inputStream.read();
            while (data != -1)
            {
                outputStream.write(data);
                data = inputStream.read();
            }
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
            if (outputStream != null)
            {
                outputStream.close();
            }
        }
    }
}