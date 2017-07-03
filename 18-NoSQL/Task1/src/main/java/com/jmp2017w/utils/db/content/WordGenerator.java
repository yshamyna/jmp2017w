package com.jmp2017w.utils.db.content;

/**
 * This class is responsible for word generation.
 */
class WordGenerator extends Generator<String>
{
    /**
     * Gets constructed word.
     *
     * @param length the length of a word.
     * @return generated word
     */
    String get(int length)
    {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            word.append(getSymbol());
        }
        return word.toString();
    }
}