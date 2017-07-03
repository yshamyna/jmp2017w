package com.jmp2017w.utils.db.content;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for providing random date.
 */
public class DateGenerator extends Generator<Date>
{
    /**
     * Gets random date.
     *
     * @return date
     */
    @Override
    public Date get()
    {
        Calendar now = Calendar.getInstance();
        long randomDateInMilliseconds = ThreadLocalRandom.current().nextLong(now.getTimeInMillis());
        return new Date(randomDateInMilliseconds);
    }
}