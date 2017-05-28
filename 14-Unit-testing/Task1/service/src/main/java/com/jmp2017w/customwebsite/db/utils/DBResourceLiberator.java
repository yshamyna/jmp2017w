package com.jmp2017w.customwebsite.db.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBResourceLiberator
{
    private DBResourceLiberator()
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
