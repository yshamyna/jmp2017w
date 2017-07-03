package com.jmp2017w.utils.db.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Base class for all generator classes.
 */
public abstract class Generator<T>
{
    /**
     * Constant. Set of symbols that can be used to construct random strings.
     */
    private static final String[] SYMBOL_SET = {"a", "b", "c", "d", "e", "f", "g", "h", "i","j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    /**
     * Gets random symbol.
     * @return a symbol
     */
    String getSymbol()
    {
        int letterToReturn = ThreadLocalRandom.current().nextInt(0, SYMBOL_SET.length);
        return SYMBOL_SET[letterToReturn];
    }

    /**
     * Gets ransom generated result.
     *
     * @return generated result.
     */
    public T get()
    {
        return null;
    }
}