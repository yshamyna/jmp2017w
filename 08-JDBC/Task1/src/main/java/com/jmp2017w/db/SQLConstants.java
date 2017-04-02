package com.jmp2017w.db;

/**
 * Contains SQL commands.
 */
public final class SQLConstants
{
    /**
     * Private constructor to prevent instantiation.
     */
    private SQLConstants()
    {
        // nothing
    }

    public static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users("
            + "id INT8 PRIMARY KEY auto_increment, "
            + "name VARCHAR(30), "
            + "surname VARCHAR(30), "
            + "birthdate TIMESTAMP);";
    public static final String CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE IF NOT EXISTS friendships("
            + "userId1 INT8, "
            + "userId2 INT8, "
            + "timestamp TIMESTAMP, "
            + "UNIQUE(userId1, userId2), "
            + "UNIQUE(userId2, userId1), "
            + "FOREIGN KEY(userId1) REFERENCES users(id), "
            + "FOREIGN KEY(userId2) REFERENCES users(id));";
    public static final String CREATE_POSTS_TABLE = "CREATE TABLE IF NOT EXISTS posts("
            + "id INT8 PRIMARY KEY auto_increment, "
            + "userId INT8, "
            + "text varchar(50), "
            + "timestamp TIMESTAMP, "
            + "FOREIGN KEY(userId) REFERENCES users(id));";
    public static final String CREATE_LIKES_TABLE = "CREATE TABLE IF NOT EXISTS likes("
            + "postId INT8, "
            + "userId INT8,"
            + "timestamp TIMESTAMP, "
            + "UNIQUE(postId, userId), "
            + "UNIQUE(userId, postId), "
            + "FOREIGN KEY(userId) REFERENCES users(id), "
            + "FOREIGN KEY(postId) REFERENCES posts(id));";

    public static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users;";
    public static final String DROP_FRIENDSHIPS_TABLE = "DROP TABLE IF EXISTS friendships;";
    public static final String DROP_POSTS_TABLE = "DROP TABLE IF EXISTS posts;";
    public static final String DROP_LIKES_TABLE = "DROP TABLE IF EXISTS likes;";

    public static final String INSERT_INTO_USERS = "INSERT INTO users(name, surname, birthdate) VALUES(?, ?, ?);";
    public static final String INSERT_INTO_FRIENDSHIPS = "INSERT INTO friendships(userId1, userId2, timestamp) VALUES(?, ?, ?);";
    public static final String INSERT_INTO_POSTS = "INSERT INTO posts(userId, text, timestamp) VALUES(?, ?, ?);";
    public static final String INSERT_INTO_LIKES = "INSERT INTO likes(postId, userId, timestamp) VALUES(?, ?, ?);";

    public static final String SELECT_USER_ID_AND_BIRTHDATE = "SELECT id, birthdate FROM users;";
    public static final String SELECT_USER_ID = "SELECT id FROM users;";
    public static final String SELECT_POST_ID_USERID_DATE = "SELECT id, userId, timestamp FROM posts;";
    public static final String SELECT_DO_TASK =
              "SELECT distinct(name) "
            + "FROM users,"
            + "    ("
            + "        SELECT userId, COUNT(*) friendCount"
            + "        FROM"
            + "            ("
            + "                SELECT userId1 userId"
            + "                FROM friendships"
            + "                UNION ALL"
            + "                SELECT userId2 userId"
            + "                FROM friendships"
            + "            )"
            + "        GROUP BY userId"
            + "        HAVING friendCount > 100"
            + "    ) friendshipData,"
            + "    ("
            + "        SELECT posts.userId postCreatorId, COUNT(*) likeCount"
            + "        FROM likes, posts"
            + "        WHERE likes.postId = posts.id AND likes.timestamp BETWEEN '2015-03-01' AND '2015-03-31'"
            + "        GROUP BY postCreatorId"
            + "        HAVING likeCount > 100"
            + "    ) likeData "
            + "WHERE users.id = likeData.postCreatorId AND users.id = friendshipData.userId;";
}