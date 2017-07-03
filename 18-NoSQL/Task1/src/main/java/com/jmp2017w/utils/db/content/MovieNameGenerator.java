package com.jmp2017w.utils.db.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for movie name generation.
 */
public class MovieNameGenerator extends Generator<String>
{
    /**
     * Movie prefix.
     */
    private static final String MOVIE_PREFIX = "movie_";
    /**
     * Constant. Minimum length of a movie.
     */
    private static final byte MIN_MOVIE_LENGTH = 5;
    /**
     * Constant. Maximum length of a movie.
     */
    private static final byte MAX_MOVIE_LENGTH = 23;

    /**
     * Participates in movie name generation. Responsible for making movie value true unique.
     */
    private static long sequence = 0L;

    /**
     * Helper class. Helps to generate words.
     */
    private WordGenerator wordGenerator = new WordGenerator();

    /**
     * Generates random movie name value.
     *
     * @return new built movie name
     */
    @Override
    public String get()
    {
        sequence++;
        int movieLength = ThreadLocalRandom.current().nextInt(MIN_MOVIE_LENGTH, MAX_MOVIE_LENGTH);
        String movieName = wordGenerator.get(movieLength) + sequence;
        return MOVIE_PREFIX + movieName;
    }
}