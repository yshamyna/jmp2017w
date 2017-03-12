package com.jmp2017w.decorator;

import com.jmp2017w.bean.Person;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class decorator for OutputStream objects.
 */
public class PersonOutputStream extends OutputStream
{
    private OutputStream out;

    /**
     * Public constructor
     * @param out {@link OutputStream} output stream
     */
    public PersonOutputStream(OutputStream out)
    {
        this.out = out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
    }

    /**
     * Writes a person to stream.
     * If person's name is not started with capital letter, the first letter is updated to it.
     * @param person {@link Person} person to write
     * @throws IOException If an I/O error occurs
     */
    public void writePerson(Person person) throws IOException
    {
        if (person != null)
        {
            StringBuilder personAsString = new StringBuilder(person.toString());
            char firstLetterOfPersonName = personAsString.charAt(0);
            if (!Character.isUpperCase(firstLetterOfPersonName))
            {
                personAsString.setCharAt(0, Character.toUpperCase(firstLetterOfPersonName));
            }
            personAsString.append('\n');

            byte[] dataToWrite = personAsString.toString().getBytes();
            out.write(dataToWrite);
        }
    }
}