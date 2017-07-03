package com.jmp2017w.utils.db.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for login generation.
 */
public class UserLoginGenerator extends Generator
{
    /**
     * Login prefix.
     */
    private static final String LOGIN_PREFIX = "login_";
    /**
     * Constant. Minimum length of a login.
     */
    private static final byte MIN_LOGIN_LENGTH = 3;
    /**
     * Constant. Maximum length of a login.
     */
    private static final byte MAX_LOGIN_LENGTH = 20;

    /**
     * Participates in login generation. Responsible for making login value true unique.
     */
    private static long sequence = 0L;

    /**
     * Helper class. Helps to generate words.
     */
    private WordGenerator wordGenerator = new WordGenerator();

    /**
     * Generates random login value.
     *
     * @return new built login
     */
    @Override
    public String get()
    {
        sequence++;
        int loginLength = ThreadLocalRandom.current().nextInt(MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH);
        String login = wordGenerator.get(loginLength) + sequence;
        return LOGIN_PREFIX + login;
    }
}