package com.jmp2017w.customwebsite.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides method for getting a connection to database.
 */
public final class DatabaseManager
{
    private DatabaseManager()
    {
        // nothing
    }

    public static Connection getConnection() throws SQLException
    {
        try
        {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:./customsitedb", "sa", "");
        }
        catch (ClassNotFoundException e)
        {
            throw new SQLException("Unable to find DB driver for accessing database.");
        }
    }
}