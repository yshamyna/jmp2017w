package com.jmp2017w;

import com.jmp2017w.utils.db.DBManager;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Facet;
import org.bson.Document;

import java.util.Arrays;

/**
 * Write simple app with a few classes reflected typical Social Network (Users, Messages, Movies, Audio tracks, Friendships and etc).
 * Keep more than 10^9 instances for each class into MongoDB.
 * Using MongoDB driver provide simple reporting based on MongoDB Aggregation Framework:
 *  - Average number of messages by day of week;
 *  - max number of new friendships from month to month;
 *  - min number of watched movies by users with more than 100 friends.
 *
 *  P.S. Play with different data models, don't fear to experiment with denormalization.
 */
public class Runner
{
    /**
     * Prints the results of the aggregation.
     */
    private static final Block<Document> printBlock = new Block<Document>()
    {
        public void apply(final Document document)
        {
            System.out.println(document.toJson());
        }
    };

    public static void main(String[] args)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("SocialNetwork");
        DBManager.prepareDatabase(database);
        averageNumberOfMessageByDayOfWeek(database);
        maxNumberOfNewFriendshipsFromMonthToMonth(database);
        minNumberOfWatchedMoviesByUsersWithMoreThan50Friends(database);
    }

    /**
     * Average number of messages by day of week.
     *
     * @param database the database.
     */
    private static void averageNumberOfMessageByDayOfWeek(MongoDatabase database)
    {
        System.out.println("Average number of message by day of week");
        MongoCollection<Document> messagesCollection = database.getCollection("messages");
        messagesCollection.aggregate(
            Arrays.asList(
                Aggregates.group(
                    new Document(
                        "date", new Document(
                            "$dateToString", new Document("format", "%Y-%m-%d").append("date", "$when")
                        )
                    ).append(
                        "weekDay", new Document("$dayOfWeek", "$when")
                    ),
                    Accumulators.sum("sum", 1)
                ),
                Aggregates.group(new Document("dayOfWeek", "$_id.weekDay"), Accumulators.avg("avgMessagesByWeekDay", "$sum")),
                Aggregates.sort(new Document("_id", 1))
            )
        ).forEach(printBlock);
    }

    /**
     * Max number of new friendships from month to month.
     *
     * @param database the database.
     */
    private static void maxNumberOfNewFriendshipsFromMonthToMonth(MongoDatabase database)
    {
        System.out.println("Max number of new friendships from month to month");
        MongoCollection<Document> friendshipsCollection = database.getCollection("friendships");
        friendshipsCollection.aggregate(
            Arrays.asList(
                Aggregates.group(
                    new Document(
                        "date", new Document(
                            "$dateToString", new Document("format", "%Y-%m").append("date", "$becomingFriendsDate")
                        )
                    ),
                    Accumulators.sum("sum", 1)
                ),
                Aggregates.group(null, Accumulators.max("result", "$sum"))
                // Aggregates.group(new Document("month", "$_id.date"), Accumulators.max("friendshipCountInTheMonth", "$sum"))
                // Aggregates.sort(new Document("_id", 1))
            )
        ).forEach(printBlock);
    }

    /**
     * Min number of watched movies by users with more than 50 friends.
     *
     * @param database the database.
     */
    private static void minNumberOfWatchedMoviesByUsersWithMoreThan50Friends(MongoDatabase database)
    {
        System.out.println("Min number of watched movies by users with more than 50 friends:");
        MongoCollection<Document> friendshipsCollection = database.getCollection("friendships");
        friendshipsCollection.aggregate(
            Arrays.asList(
                Aggregates.facet(new Facet("user", new Document("$sortByCount", "$user1"))),
                Aggregates.unwind("$user"),
                Aggregates.match(new Document("user.count", new Document("$gt", 50))),
                Aggregates.lookup("users", "user._id", "login", "user1Information"),
                Aggregates.unwind("$user1Information"),
                Aggregates.project(new Document("item", 1).append("watchedMoviesSize", new Document("$size", "$user1Information.watchedMovies"))),
                Aggregates.group(null, Accumulators.min("result", "$watchedMoviesSize"))
            )
        ).forEach(printBlock);
    }

}