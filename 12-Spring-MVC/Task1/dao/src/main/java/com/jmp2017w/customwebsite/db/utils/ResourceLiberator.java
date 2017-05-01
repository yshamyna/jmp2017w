package com.jmp2017w.customwebsite.db.utils;

import java.io.IOException;
import java.io.Reader;

public final class ResourceLiberator
{
    private ResourceLiberator()
    {
    }

    public static void closeReaders(Reader... readers)
    {
        if (readers != null)
        {
            for (Reader reader : readers)
            {
                if (reader == null)
                {
                    continue;
                }

                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    // nothing
                }
            }
        }
    }
}
