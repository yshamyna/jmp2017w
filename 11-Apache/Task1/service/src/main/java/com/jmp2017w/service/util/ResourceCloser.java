package com.jmp2017w.service.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is responsible for closing resources.
 */
public final class ResourceCloser
{
    private ResourceCloser()
    {

    }

    public static void closeConnections(Connection... connections)
    {
        if (connections != null)
        {
            for (Connection connection : connections)
            {
                if (connection == null)
                {
                    continue;
                }

                try
                {
                    if (!connection.isClosed())
                    {
                        connection.close();
                    }
                }
                catch (SQLException e)
                {
                    // nothing
                }
            }
        }
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

    public static void closeResultSets(ResultSet... resultSets)
    {
        if (resultSets != null)
        {
            for (ResultSet resultSet : resultSets)
            {
                if (resultSet == null)
                {
                    continue;
                }

                try
                {
                    resultSet.close();
                }
                catch (SQLException e)
                {
                    // nothing
                }
            }
        }
    }

    public static void closeStatements(Statement... statements)
    {
        if (statements != null)
        {
            for (Statement statement : statements)
            {
                if (statement == null)
                {
                    continue;
                }

                try
                {
                    statement.close();
                }
                catch (SQLException e)
                {
                    // nothing
                }
            }
        }
    }
}
