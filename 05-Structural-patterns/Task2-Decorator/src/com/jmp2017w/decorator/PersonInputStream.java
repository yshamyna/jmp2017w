package com.jmp2017w.decorator;

import com.jmp2017w.bean.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class decorator for InputStream objects.
 */
public class PersonInputStream extends InputStream
{
    private byte[] buffer = new byte[20];
    private int countOfBytesReadLastTime = Integer.MIN_VALUE;
    private Queue<Person> personQueue;
    private StringBuffer sb;
    private InputStream in;

    /**
     * Public constructor.
     * @param in {@link InputStream} input stream.
     */
    public PersonInputStream(InputStream in)
    {
        this.in = in;
        personQueue = new LinkedList<Person>();
        sb = new StringBuffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException
    {
        if (in == null)
        {
            return -1;
        }
        return in.read();
    }

    /**
     * Reads data from the stream and gets {@link Person} object.
     * @return {@link Person} object
     * @throws IOException If any I/O error occurs
     */
    public Person readPerson() throws IOException
    {
        if (personQueue.isEmpty())
        {
            countOfBytesReadLastTime = in.read(buffer);
            if (!isEOFReached())
            {
                queuePersons();
            }

            if (personQueue.isEmpty() && sb.length() > 0)
            {
                return readPerson();
            }
            return personQueue.poll();
        }
        return personQueue.poll();
    }

    /**
     * Converts bytes to {@link Person} objects and put them to inner queue.
     * If name of a {@link Person} is not started with lower case, the first letter is updated to it.
     */
    private void queuePersons()
    {
        char ch;
        for (int i = 0; i < countOfBytesReadLastTime; i++)
        {
            ch = (char) buffer[i];
            if (ch == '\n')
            {
                if (sb.length() > 0)
                {
                    char firstLetterOfPersonName = sb.charAt(0);
                    if (!Character.isLowerCase(firstLetterOfPersonName))
                    {
                        sb.setCharAt(0, Character.toLowerCase(firstLetterOfPersonName));
                    }
                    personQueue.add(new Person(sb.toString()));
                }
                sb = new StringBuffer();
            }
            else
            {
                sb.append(ch);
            }
        }
    }

    /**
     * Indicates whether all data from the stream were read.
     * @return <code>true</code> if data were read, <code>false</code> if data weren't read completely.
     */
    public boolean isDataAbsentForReading()
    {
        return isEOFReached() && personQueue.isEmpty();
    }

    /**
     * Checks whether EOF was reached
     * @return <code>true</code> if EOD was reached, otherwise - <code>false</code>
     */
    private boolean isEOFReached()
    {
        return countOfBytesReadLastTime == -1;
    }
}