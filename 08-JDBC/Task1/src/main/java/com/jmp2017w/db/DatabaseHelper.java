package com.jmp2017w.db;

import com.jmp2017w.bean.Post;
import com.jmp2017w.bean.User;
import com.jmp2017w.util.ApplicationUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is responsible for DB schema creation and DB population.
 */
final public class DatabaseHelper
{
    /**
     * Public constructor.
     */
    public DatabaseHelper()
    {
        // nothing
    }

    /**
     * Gets database ready. After this operation schema of DB will be created and populated.
     * @throws SQLException if SQL operations were failed
     */
    public void initializeDatabase() throws SQLException
    {
        System.out.println("Initializing the database.");
        try (Connection connection = DatabaseManager.getConnection())
        {
            prepareSchema(connection);
            populateDatabase(connection);
        }
        catch (SQLException e)
        {
            throw new SQLException(e);
        }
        System.out.println("The database is ready for use.");
    }

    /**
     * Drops and creates tables.
     * @param connection a connection to database
     * @throws SQLException if SQL operations were failed
     */
    private void prepareSchema(Connection connection) throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append(SQLConstants.DROP_LIKES_TABLE).append(SQLConstants.DROP_POSTS_TABLE)
                .append(SQLConstants.DROP_FRIENDSHIPS_TABLE).append(SQLConstants.DROP_USERS_TABLE)
                .append(SQLConstants.CREATE_USERS_TABLE).append(SQLConstants.CREATE_FRIENDSHIPS_TABLE)
                .append(SQLConstants.CREATE_POSTS_TABLE).append(SQLConstants.CREATE_LIKES_TABLE);
        try (PreparedStatement createSchema = connection.prepareStatement(sql.toString()))
        {
            createSchema.executeUpdate();
            System.out.println("Schema 'society' has been created.");
        }
    }

    /**
     * Populates the database.
     * @param connection a connection to database
     * @throws SQLException if SQL operations were failed
     */
    private void populateDatabase(Connection connection) throws SQLException
    {
        populateUsersTable(connection);
        populateFriendshipsTable(connection);
        populatePostsTable(connection);
        populateLikesTable(connection);
    }

    /**
     * Populates 'users' table.
     * @param connection a connection to DB
     * @throws SQLException if SQL operations were failed
     */
    private void populateUsersTable(Connection connection) throws SQLException
    {
        try (PreparedStatement insertIntoUsersTable = connection.prepareStatement(SQLConstants.INSERT_INTO_USERS))
        {
            List<String> firstNames = ApplicationUtil.getNames("FirstNames.txt");
            List<String> lastNames = ApplicationUtil.getNames("LastNames.txt");
            for (String firstName : firstNames)
            {
                for (String lastName : lastNames)
                {
                    insertIntoUsersTable.setString(1, firstName);
                    insertIntoUsersTable.setString(2, lastName);
                    insertIntoUsersTable.setTimestamp(3, new Timestamp(ApplicationUtil.randomDateInMilliseconds()));
                    insertIntoUsersTable.addBatch();
                }
            }
            int[] arrayOfUpdateCounts = insertIntoUsersTable.executeBatch();
            System.out.println(arrayOfUpdateCounts.length + " records were inserted into table 'users'");
        }
        catch (IOException e)
        {
           throw new SQLException("Unable to populate table 'users' as seed data cannot be read.");
        }
    }

    /**
     * Populates 'friendships' table.
     * @param connection a connection to DB
     * @throws SQLException if SQL operations were failed
     */
    private void populateFriendshipsTable(Connection connection) throws SQLException
    {
        try
        (
            PreparedStatement insertIntoFriendshipsTable = connection.prepareStatement(SQLConstants.INSERT_INTO_FRIENDSHIPS);
            Statement selectUserInfo = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet userInformation = selectUserInfo.executeQuery(SQLConstants.SELECT_USER_ID_AND_BIRTHDATE)
        )
        {
            if (userInformation.last())
            {
                int rowCountInResultSet = userInformation.getRow();
                User[] users = new User[rowCountInResultSet];
                users[rowCountInResultSet - 1] = new User(userInformation.getLong("id"), null, null,
                        userInformation.getTimestamp("birthdate"));

                for (int i = rowCountInResultSet - 2; userInformation.previous(); i--)
                {
                    users[i] = new User(userInformation.getLong("id"), null, null, userInformation.getTimestamp("birthdate"));
                }

                for (int i = 0; i < users.length - 1; i++)
                {
                    for (int j = i + 1; j < users.length; j++)
                    {
                        if (!ApplicationUtil.isFriendshipCanBeBetweenTwoUsers())
                        {
                            continue;
                        }

                        long birthdateOfFirstUserInMilliseconds = users[i].getBirthdate().getTime();
                        long birthdateOfSecondUserInMilliseconds = users[j].getBirthdate().getTime();
                        long friendshipEstablishmentDate = ApplicationUtil.randomFriendshipDate(birthdateOfFirstUserInMilliseconds,
                                birthdateOfSecondUserInMilliseconds);

                        insertIntoFriendshipsTable.setLong(1, users[i].getId());
                        insertIntoFriendshipsTable.setLong(2, users[j].getId());
                        insertIntoFriendshipsTable.setTimestamp(3, new Timestamp(friendshipEstablishmentDate));
                        insertIntoFriendshipsTable.addBatch();
                    }
                }
                int[] arrayOfUpdateCounts = insertIntoFriendshipsTable.executeBatch();
                System.out.println(arrayOfUpdateCounts.length + " records were inserted into table 'friendships'");
            }
        }
    }

    /**
     * Populates 'posts' table.
     * @param connection a connection to DB
     * @throws SQLException if SQL operations were failed
     */
    private void populatePostsTable(Connection connection) throws SQLException
    {
        try
        (
            PreparedStatement insertIntoPostsTable = connection.prepareStatement(SQLConstants.INSERT_INTO_POSTS);
            Statement selectUserInfo = connection.createStatement();
            ResultSet userInformation = selectUserInfo.executeQuery(SQLConstants.SELECT_USER_ID_AND_BIRTHDATE)
        )
        {
            while (userInformation.next())
            {
                Long userId = userInformation.getLong("id");
                Timestamp birthdate = userInformation.getTimestamp("birthdate");

                List<String> posts = ApplicationUtil.randomSetOfPostTexts();
                for (String post : posts)
                {
                    insertIntoPostsTable.setLong(1, userId);
                    insertIntoPostsTable.setString(2, post);
                    insertIntoPostsTable.setTimestamp(3, new Timestamp(ApplicationUtil.randomPostDate(birthdate.getTime())));
                    insertIntoPostsTable.addBatch();
                }
            }
            int[] arrayOfUpdateCounts = insertIntoPostsTable.executeBatch();
            System.out.println(arrayOfUpdateCounts.length + " records were inserted into table 'posts'");
        }
    }

    /**
     * Populates 'likes' table.
     * @param connection a connection to DB
     * @throws SQLException if SQL operations were failed
     */
    private void populateLikesTable(Connection connection) throws SQLException
    {
        try
        (
            PreparedStatement insertIntoLikesTable = connection.prepareStatement(SQLConstants.INSERT_INTO_LIKES);
            Statement selectUserInfo = connection.createStatement();
            ResultSet userInformation = selectUserInfo.executeQuery(SQLConstants.SELECT_USER_ID);
            Statement selectPostInfo = connection.createStatement();
            ResultSet postInformation = selectPostInfo.executeQuery(SQLConstants.SELECT_POST_ID_USERID_DATE)
        )
        {
            List<User> users = new LinkedList<>();
            while (userInformation.next())
            {
                users.add(new User(userInformation.getLong("id"), null, null, null));
            }

            List<Post> posts = new LinkedList<>();
            while (postInformation.next())
            {
                posts.add(new Post(postInformation.getLong("id"), postInformation.getLong("userId"), null,
                        postInformation.getTimestamp("timestamp")));
            }

            for (User user : users)
            {
                for (Post post : posts)
                {
                    if (post.getUserId().equals(user.getId()) || !ApplicationUtil.doesUserLikePost())
                    {
                        continue;
                    }

                    insertIntoLikesTable.setLong(1, post.getId());
                    insertIntoLikesTable.setLong(2, user.getId());
                    long likeDate = ApplicationUtil.randomLikeDate(post.getAddedDate().getTime());
                    insertIntoLikesTable.setTimestamp(3, new Timestamp(likeDate));
                    insertIntoLikesTable.addBatch();
                }
            }

            int[] arrayOfUpdateCounts = insertIntoLikesTable.executeBatch();
            System.out.println(arrayOfUpdateCounts.length + " records were inserted into table 'likes'");
        }
    }
}