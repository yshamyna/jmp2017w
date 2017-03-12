package com.jmp2017w;

import com.jmp2017w.bean.Person;
import com.jmp2017w.decorator.PersonInputStream;
import com.jmp2017w.decorator.PersonOutputStream;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Create a PersonInputStream that implements the readPerson() method and can decorate any given InputStream.
 * Create a PersonOutputStream that implements the writePerson(Person) method that returns a Person and can decorate any given
 * OutputStream.
 *
 * The PersonOutputStream decorator must check if the name of the person starts with a capital letter and if it doesn't – it should
 * update it before writing it to the destination.
 * The PersonInputStream decorator must check if the name of the person starts with a low letter and if it doesn’t – it should
 * update it before writing it to the destination.
 *
 * Write a program that uses the two decorators to write and read persons to and from a file.
 */
public class Runner
{
    private static final String FILE_NAME = "result.txt";

    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        List<Person> persons = createPersons();
        System.out.println("Persons to write:");
        print(persons);
        File file = new File(FILE_NAME);
        writePersons(persons, file);

        List<Person> personsFromFile = readPersons(file);
        System.out.println("\nPersons from the file:");
        print(personsFromFile);
    }

    /**
     * Creates and gets list of persons.
     * @return a list of persons
     */
    private static List<Person> createPersons()
    {
        List<Person> persons = new LinkedList<Person>();
        String[] names = {"john", "tracy", "patric", "luisa", "Adam", "Sasha", "donald", "Miguel Sixteenth", "O"};
        for (String personName : names)
        {
            persons.add(new Person(personName));
        }
        return persons;
    }

    /**
     * Writes persons to specified file.
     * @param persons list of persons
     * @param file destination file
     */
    private static void writePersons(List<Person> persons, File file)
    {
        if (persons == null || persons.isEmpty() || file == null)
        {
            return;
        }

        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file);
            PersonOutputStream personOutputStream = new PersonOutputStream(fileOutputStream);
            for (Person person : persons)
            {
                personOutputStream.writePerson(person);
            }
        }
        catch (IOException e)
        {
            System.out.println("Unable to write persons to specified file: " + file.getAbsolutePath());
        }
        finally
        {
            closeStream(fileOutputStream);
        }
    }

    /**
     * Reads file and males list of persons.
     * @param file a file
     * @return {@link List<Person>} a list of persons
     */
    private static List<Person> readPersons(File file)
    {
        List<Person> persons = new LinkedList<Person>();
        if (file != null)
        {
            FileInputStream fileInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(file);
                PersonInputStream personInputStream = new PersonInputStream(fileInputStream);
                Person person;
                while (!personInputStream.isDataAbsentForReading())
                {
                    person = personInputStream.readPerson();
                    if (person != null)
                    {
                        persons.add(person);
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Exception while reading persons from the file: " + file.getAbsolutePath());
            }
            finally
            {
                closeStream(fileInputStream);
            }
        }
        return persons;
    }

    /**
     * Closes input stream.
     * @param stream the stream to close.
     */
    private static void closeStream(Closeable stream)
    {
        if (stream != null)
        {
            try
            {
                stream.close();
            }
            catch (IOException e)
            {
                System.out.println("Exception while closing a stream.");
            }
        }
    }

    /**
     * Prints <tt>List</tt> collection.
     * @param list a list
     */
    private static void print(List<?> list)
    {
        if (list != null)
        {
            for (Object object : list)
            {
                System.out.println(object);
            }
        }
    }
}