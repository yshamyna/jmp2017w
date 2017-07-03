package com.jmp2017w.utils.db;

import com.jmp2017w.beans.Movie;
import com.jmp2017w.beans.User;
import com.jmp2017w.utils.db.content.DateGenerator;
import com.jmp2017w.utils.db.content.MessageTextGenerator;
import com.jmp2017w.utils.db.content.MovieNameGenerator;
import com.jmp2017w.utils.db.content.UserLoginGenerator;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
public class DBManager
{
    /**
     * Indicates how many movies to add to the database.
     */
    private static final int MOVIE_COUNT_TO_GENERATE = 2000;
    /**
     * Indicates how many users to add to the database.
     */
    private static final int USER_COUNT_TO_GENERATE = 10000;
    /**
     * Indicates max count of messages that one user can send to another.
     */
    private static final int MAX_SENT_MESSAGES_COUNT = 10;

    /**
     * Gets the database prepared.
     *
     * @param database the database
     */
    public static void prepareDatabase(MongoDatabase database)
    {
        if (database == null)
        {
            return;
        }

        populateDatabase(database);
    }

    /**
     * Recreates 'movies' collection and populates it.
     *
     * @param database the database.
     */
    private static void populateDatabase(MongoDatabase database)
    {
        MongoCollection<Document> moviesCollection = database.getCollection("movies");
        moviesCollection.drop();

        MovieNameGenerator movieNameGenerator = new MovieNameGenerator();
        Movie[] movies = new Movie[MOVIE_COUNT_TO_GENERATE];
        for (int i = 0; i < MOVIE_COUNT_TO_GENERATE; i++)
        {
            movies[i] = new Movie(movieNameGenerator.get());
        }

        List<Document> movieDocuments = new ArrayList<Document>(MOVIE_COUNT_TO_GENERATE);
        for (Movie movie : movies)
        {
            movieDocuments.add(new Document("name", movie.getName()));
        }
        moviesCollection.insertMany(movieDocuments);
        System.out.println("Movies were added.");

        populateDatabase(database, movies);
    }

    /**
     * Recreates 'users' collection and populates it.
     *
     * @param database the database.
     * @param movies existing movies in DB.
     */
    private static void populateDatabase(MongoDatabase database, Movie[] movies)
    {
        MongoCollection<Document> usersCollection = database.getCollection("users");
        usersCollection.drop();

        UserLoginGenerator userLoginGenerator = new UserLoginGenerator();
        User[] users = new User[USER_COUNT_TO_GENERATE];
        for (int i = 0; i < USER_COUNT_TO_GENERATE; i++)
        {
            users[i] = new User(userLoginGenerator.get());
        }

        List<Document> userDocuments = new ArrayList<Document>(MOVIE_COUNT_TO_GENERATE);
        Set<String> watchedMovies;
        for (User user : users)
        {
            watchedMovies = makeWatchedList(movies, 0.05);
            userDocuments.add(new Document("login", user.getLogin()).append("watchedMovies", watchedMovies));
        }
        usersCollection.insertMany(userDocuments);
        System.out.println("Users were added.");

        populateDatabase(database, users);
    }

    /**
     * Recreates 'messages' and 'friendships' collections, and populates them.
     *
     * @param database the database.
     * @param users all users.
     */
    private static void populateDatabase(MongoDatabase database, User[] users)
    {
        MongoCollection<Document> messagesCollection = database.getCollection("messages");
        messagesCollection.drop();
        MongoCollection<Document> friendshipsCollection = database.getCollection("friendships");
        friendshipsCollection.drop();

        DateGenerator dateGenerator = new DateGenerator();
        MessageTextGenerator messageTextGenerator = new MessageTextGenerator();

        for (int i = 0; i < users.length - 1; i++)
        {
            for (int j = i + 1; j < users.length; j++)
            {
                boolean wereUsersSpeaking = (1.0 - Math.random()) <= 0.006;
                if (!wereUsersSpeaking)
                {
                    continue;
                }

                int messageCount = ThreadLocalRandom.current().nextInt(MAX_SENT_MESSAGES_COUNT) + 1;
                List<Document> messageDocuments = new ArrayList<Document>(messageCount);
                for (int k = 0; k < messageCount; k++)
                {
                    messageDocuments.add(new Document("sender", users[i].getLogin())
                            .append("receiver", users[j].getLogin())
                            .append("text", messageTextGenerator.get())
                            .append("when", dateGenerator.get()));
                }
                messagesCollection.insertMany(messageDocuments);

                boolean areUsersFriends = (1.0 - Math.random()) <= 0.5;
                if (areUsersFriends)
                {
                    Date becomingFriendsDate =  dateGenerator.get();
                    friendshipsCollection.insertOne(new Document("user1", users[i].getLogin())
                            .append("user2", users[j].getLogin())
                            .append("becomingFriendsDate", becomingFriendsDate));
                    friendshipsCollection.insertOne(new Document("user1", users[j].getLogin())
                            .append("user2", users[i].getLogin())
                            .append("becomingFriendsDate", becomingFriendsDate));
                }
            }
        }

        System.out.println("Messages were added.");
        System.out.println("Friendships were added.");
    }


    // -------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------ Helper methods ---------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets watched movies.
     *
     * @param movies all movies.
     * @param chanceMovieGotWatched indicates change that current movie was watched by user.
     * @return list of watched movies
     */
    private static Set<String> makeWatchedList(Movie[] movies, double chanceMovieGotWatched)
    {
        Set<String> watchedMovieNames = new HashSet<String>();
        for (Movie movie : movies)
        {
            boolean shouldMovieBeAddedToWatchedList = (1.0 - Math.random()) <= chanceMovieGotWatched;
            if (shouldMovieBeAddedToWatchedList)
            {
                watchedMovieNames.add(movie.getName());
            }
        }
        return watchedMovieNames;
    }
}