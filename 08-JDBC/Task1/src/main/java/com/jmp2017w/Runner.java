package com.jmp2017w;

import com.jmp2017w.db.DatabaseHelper;
import com.jmp2017w.db.DatabaseManager;
import com.jmp2017w.db.SQLConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 1) Create simple database with tables:
 *    - Users (id, name, surname, birthdate);
 *    - Friendships (userid1, userid2, timestamp);
 *    - Posts(id, userId, text, timestamp);
 *    - Likes (postid, userid, timestamp).
 *
 * 2) Fill tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes).
 *
 * 3) Program should print out all names (only distinct) of users who has more then 100 friends and 100 likes in March 2015.
 *
 * Note: all actions (table creation, insert data and reading) should be implemented with JDBC.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        try
        {
            DatabaseHelper databaseHelper = new DatabaseHelper();
            databaseHelper.initializeDatabase();
            doTask();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Print out all names (only distinct) of users who has more when 100 friends and 100 likes in March 2015.
     * @throws SQLException if SQL operations were failed
     */
    private static void doTask() throws SQLException
    {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Names of users that have more than 100 friends and who has more than 100 likes in March 2015:");

        try
        (
            Connection connection = DatabaseManager.getConnection();
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery(SQLConstants.SELECT_DO_TASK)
        )
        {
            int number = 1;
            while (result.next())
            {
                System.out.println(String.format("%d. %s", number++, result.getString("name")));
            }
        }
    }
}