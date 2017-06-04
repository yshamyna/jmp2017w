package com.jmp2017w.classic;

import com.jmp2017w.bean.Author;
import com.jmp2017w.classic.util.AuthorHelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * а) Get the unique surnames in uppercase of the first 15 book authors that are 50 years classic or older.
 * b) Print out the sum of ages of all female authors younger than 25
 */
public class Runner
{


    public static void main(String[] args)
    {
        List<Author> authors = AuthorHelper.generate(AuthorHelper.DEFAULT_AUTHOR_QUANTITY);

        //--------------------------------------------------------------------------------------------------------------------------

        System.out.println("Get the unique surnames in uppercase of the first 15 book authors that are 50 years classic or older.");
        final int quantity = 15;
        final int age = 50;
        Set<String> uniqueSurnamesInUppercase = getUniqueSurnames(authors, age, quantity);
        for (String uniqueSurname : uniqueSurnamesInUppercase)
        {
            System.out.println(uniqueSurname);
        }

        //--------------------------------------------------------------------------------------------------------------------------

        System.out.println("Print out the sum of ages of all female authors younger than 25.");
        final int femaleAge = 25;
        int sum = getSumOfAgesOfAllFemale(authors, femaleAge);
        System.out.println(sum);
    }

    /**
     * Gets the unique surnames in uppercase of the first @quantity book authors that are @age years classic or older.
     * @param authors list of authors
     * @param age the age
     * @param quantity the quantity
     * @return unique surnames
     */
    private static Set<String> getUniqueSurnames(Collection<Author> authors, int age, int quantity)
    {
        List<String> surnames = new LinkedList<>();
        for (Author author : authors)
        {
            if (author.getAge() >= age)
            {
                surnames.add(author.getSurname());
                if (surnames.size() == quantity)
                {
                    break;
                }
            }

        }
        Set<String> uniqueSurnamesInUppercase = new HashSet<>();
        for (String surnameToCheck : surnames)
        {
            int surnameCount = 0;
            for (String surname : surnames)
            {
                if (surname.equals(surnameToCheck))
                {
                    surnameCount++;
                    if (surnameCount > 1)
                    {
                        break;
                    }
                }
            }
            if (surnameCount == 1)
            {
                uniqueSurnamesInUppercase.add(surnameToCheck.toUpperCase());
            }
        }
        return uniqueSurnamesInUppercase;
    }

    /**
     * Gets the sum of ages of all female authors younger than @age
     * @param authors list of authors
     * @param age the age
     * @return the sum
     */
    private static int getSumOfAgesOfAllFemale(Collection<Author> authors, int age)
    {
        int sum = 0;
        for (Author author : authors)
        {
            if (author.isFemale() && author.getAge() < age)
            {
                sum += author.getAge();
            }
        }
        return sum;
    }
}