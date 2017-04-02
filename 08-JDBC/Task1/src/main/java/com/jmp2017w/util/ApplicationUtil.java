package com.jmp2017w.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Util class. Provides helper methods for populating the database.
 */
public final class ApplicationUtil
{
    /**
     * Max birth date.
     */
    private static final long MAX_RANDOM_DATE;
    private static final long ONE_YEAR_IN_MILLISECONDS = 31536000000L;
    private static final long SEVEN_YEARS_IN_MILLISECONDS = 7 * ONE_YEAR_IN_MILLISECONDS;

    static
    {
        Calendar lastPossibleDate = Calendar.getInstance();
        lastPossibleDate.set(Calendar.HOUR, 0);
        lastPossibleDate.set(Calendar.MINUTE, 0);
        lastPossibleDate.set(Calendar.SECOND, 0);
        lastPossibleDate.set(Calendar.MILLISECOND, 0);
        lastPossibleDate.set(Calendar.YEAR, 2000);
        lastPossibleDate.set(Calendar.MONTH, 1);
        lastPossibleDate.set(Calendar.DAY_OF_MONTH, 1);
        MAX_RANDOM_DATE = lastPossibleDate.getTimeInMillis();
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private ApplicationUtil()
    {
        // nothing
    }

    /**
     * Gets names from input file.
     * @param filename name if a file
     * @return list of names
     * @throws IOException in file not found or error while reading occurred
     */
    public static List<String> getNames(String filename) throws IOException
    {
        List<String> names = new LinkedList<>();
        if (filename != null)
        {
            try (InputStream is = ApplicationUtil.class.getClassLoader().getResourceAsStream(filename);
                 Scanner scanner = new Scanner(is))
            {
                while (scanner.hasNext())
                {
                    names.add(scanner.next());
                }
            }
            catch (IOException e)
            {
                throw new IOException(e);
            }
        }
        return names;
    }

    /**
     * Generates random date between January 1, 1970 and January 1, 2000.
     * @return date in milliseconds
     */
    public static long randomDateInMilliseconds()
    {
        return (long) (Math.random() * MAX_RANDOM_DATE);
    }

    /**
     * Generates random date when friendship had been established between two users.
     * The date is between 7 years since birth date of youngest user and today time.
     * @param birthDateOfFirstUser birth date in millisecond of first user
     * @param birthDateOfSecondUser birth date in milliseconds of second user
     * @return friendship date in milliseconds
     */
    public static long randomFriendshipDate(long birthDateOfFirstUser, long birthDateOfSecondUser)
    {
        return randomDate(birthDateOfFirstUser, birthDateOfSecondUser, SEVEN_YEARS_IN_MILLISECONDS);
    }

    /**
     * Denotes whether friendship can be established. The chance is 6%.
     * @return <true>code</true> if friendship can be established; <code>false</code> in other cases.
     */
    public static boolean isFriendshipCanBeBetweenTwoUsers()
    {
        return (1.0 - Math.random()) <= 0.06;
    }

    /**
     * Generates date when post was added. Post date will after birth date of user.
     * @param usersBirthDate birth date of user.
     * @return random date within time frame [birth date + 7 years, NOW]
     */
    public static long randomPostDate(long usersBirthDate)
    {
        return randomDate(usersBirthDate, -1, SEVEN_YEARS_IN_MILLISECONDS);
    }

    /**
     * Generates date when like was added to a post. Date of like will after date when post was published.
     * The chance is 27% that the date will be in March, 2015.
     * @param postDate date when a post was added.
     * @return random date within time frame [post date + 7 years, NOW] or [01 March, 2015 - 31 March, 2015 ]
     */
    public static long randomLikeDate(long postDate)
    {
        if ((1.0 - Math.random()) <= 0.27)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date endOfMarch2015Year = sdf.parse("2015-03-31");
                if (endOfMarch2015Year.getTime() >= postDate)
                {
                    Date beginningOfMarch2015Year = sdf.parse("2015-03-01");
                    long threshold = endOfMarch2015Year.getTime() - beginningOfMarch2015Year.getTime();
                    return ((long) (Math.random() * threshold)) + beginningOfMarch2015Year.getTime();
                }
            }
            catch (ParseException e)
            {
                // nothing
            }
        }
        return randomDate(postDate, -1, 0);
    }

    /**
     * Gets random number of posts added by an user.
     * @return max 100 posts with up to 5 words in a sentence for each post.
     */
    public static List<String> randomSetOfPostTexts()
    {
        List<String> posts = new LinkedList<>();
        long postNumber = getRandomCountOfPostsThatUserAdded();
        for (long i = 0; i < postNumber; i++)
        {
            posts.add(generatePostText());
        }
        return posts;
    }

    /**
     * Denotes whether user likes a post. The chance is 0.25%.
     * @return <true>code</true> if user likes a post; <code>false</code> in other cases.
     */
    public static boolean doesUserLikePost()
    {
        return (1.0 - Math.random()) <= 0.0025;
    }

    /**
     * Generates a sentence for "post". The text will contain 1-5 words.
     * @return post text
     */
    private static String generatePostText()
    {
        final int maxWordsInTheText = 5;
        final int maxWordNumber = 10000;

        int wordCount = (int) (Math.random() * maxWordsInTheText) + 1;
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < wordCount; i++)
        {
            int wordNumber = (int) (Math.random() * maxWordNumber) + 1;
            text.append("Word").append(wordNumber);
            text.append(i + 1 == wordCount ? "." : " ");
        }
        return text.toString();
    }

    /**
     * Gets count of posts added by user.
     * @return random number up to 100.
     */
    private static long getRandomCountOfPostsThatUserAdded()
    {
        final long maxPostedTopics = 100;
        return ((long) (Math.random() * maxPostedTopics)) + 1;
    }

    /**
     * Generates random date. The date is between @lowLimit years since latest date and today time.
     * @param date1 first date in milliseconds
     * @param date2 second date in milliseconds
     * @param lowLimit denotes how min shift from MAX(date, date2)
     * @return random date within time frame [MAX(date1, date2) + lowLimit years, NOW]
     */
    private static long randomDate(long date1, long date2, long lowLimit)
    {
        long latestDate = Math.max(date1, date2);
        final long minTimeThatShouldPass = lowLimit + latestDate;
        final long maxTimeThatShouldPass = Calendar.getInstance().getTimeInMillis();
        final long threshold = maxTimeThatShouldPass - minTimeThatShouldPass;
        return ((long) (Math.random() * threshold)) + minTimeThatShouldPass;
    }
}