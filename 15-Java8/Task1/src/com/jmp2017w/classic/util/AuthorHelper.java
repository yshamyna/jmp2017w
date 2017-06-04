package com.jmp2017w.classic.util;

import com.jmp2017w.bean.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
public final class AuthorHelper
{
    private static final String[] surnames = {"Smith", "Jones", "Williams", "Taylor", "Brown", "Davies", "Evans", "Wilson",
            "Thomas", "Johnson", "Roberts", "Robinson", "Thompson", "Wright", "Walker", "White", "Edwards", "Hughes", "Green",
            "Hall", "Lewis", "Harris", "Clarke", "Patel", "Jackson", "Wood", "Turner", "Martin", "Cooper", "Hill", "Ward",
            "Morris", "Moore", "Clark", "Lee"};
    private static final Integer LOW_AGE_LIMIT = 20;
    private static final Integer HIGH_AGE_LIMIT_EXCLUSIVE = 91;
    public static final Integer DEFAULT_AUTHOR_QUANTITY = 50;

    private AuthorHelper()
    {
        // nothing
    }

    public static List<Author> generate(int quantity)
    {
        List<Author> authors = new ArrayList<>();
        if (quantity < 1)
        {
            return authors;
        }

        for (int i = 0; i < quantity; i++)
        {
            Author author = new Author();
            author.setAge(ThreadLocalRandom.current().nextInt(LOW_AGE_LIMIT, HIGH_AGE_LIMIT_EXCLUSIVE));
            author.setSurname(surnames[ThreadLocalRandom.current().nextInt(0, surnames.length)]);
            author.setSex(ThreadLocalRandom.current().nextBoolean());

            authors.add(author);
        }

        return authors;
    }
}