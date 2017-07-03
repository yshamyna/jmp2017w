package com.jmp2017w.utils.db.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for generating message text.
 */
public class MessageTextGenerator extends Generator<String>
{
    /**
     * Constant. Space letter.
     */
    private static final String SPACE = " ";
    /**
     * Constant. Min words count in text message.
     */
    private static final byte MIN_WORD_COUNT_IN_SENTENCE_LENGTH = 1;
    /**
     * Constant. Max words count in text message.
     */
    private static final byte MAX_WORD_COUNT_IN_SENTENCE_LENGTH = 30;
    /**
     * Constant. Minimum length of a word.
     */
    private static final byte MIN_WORD_LENGTH = 1;
    /**
     * Constant. Maximum length of a word.
     */
    private static final byte MAX_WORD_LENGTH = 25;

    /**
     * Helper class. Helps to generate words.
     */
    private WordGenerator wordGenerator = new WordGenerator();

    /**
     * Generates random message text.
     *
     * @return message text
     */
    @Override
    public String get()
    {
        StringBuilder message = new StringBuilder();
        int wordCount = ThreadLocalRandom.current().nextInt(MIN_WORD_COUNT_IN_SENTENCE_LENGTH, MAX_WORD_COUNT_IN_SENTENCE_LENGTH);
        for (int i = 0; i < wordCount; i++)
        {
            int wordLength = ThreadLocalRandom.current().nextInt(MIN_WORD_LENGTH, MAX_WORD_LENGTH);
            message.append(wordGenerator.get(wordLength)).append(SPACE);
        }
        message.deleteCharAt(message.length() - 1);
        return message.toString();
    }
}