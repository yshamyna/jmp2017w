package com.jmp2017w.db;

import com.jmp2017w.service.util.ResourceCloser;
import org.h2.tools.RunScript;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Contains helper method to easily manage DB content.
 */
public class DBHelper
{
    public Boolean initializeDatabase()
    {
        Boolean initializationSuccessful = true;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("db/init.sql");
        InputStreamReader initDatabaseReader = null;
        Connection connection = null;
        try
        {
            initDatabaseReader = new InputStreamReader(is);
            connection = DatabaseManager.getConnection();
            RunScript.execute(connection, initDatabaseReader);
        }
        catch (Exception e)
        {
            initializationSuccessful = false;
        }
        finally
        {
            ResourceCloser.closeReaders(initDatabaseReader);
            ResourceCloser.closeConnections(connection);
        }

        return initializationSuccessful;
    }
}