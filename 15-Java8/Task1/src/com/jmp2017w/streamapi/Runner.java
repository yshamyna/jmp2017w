package com.jmp2017w.streamapi;

import com.jmp2017w.bean.Author;
import com.jmp2017w.classic.util.AuthorHelper;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

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
        Set<String> uniqueSurnames = authors.stream()
                .filter(author -> author.getAge() >= 50).limit(15).collect(groupingBy(Author::getSurname, counting()))
                .entrySet().stream()
                .filter(item -> item.getValue() == 1).map(item -> item.getKey().toUpperCase()).collect(toSet());
        uniqueSurnames.forEach(System.out::println);

        //--------------------------------------------------------------------------------------------------------------------------

        System.out.println("Print out the sum of ages of all female authors younger than 25");
        int sum = authors.stream().filter(author -> author.isFemale() && author.getAge() < 25).mapToInt(Author::getAge).sum();
        System.out.println(sum);
    }
}
