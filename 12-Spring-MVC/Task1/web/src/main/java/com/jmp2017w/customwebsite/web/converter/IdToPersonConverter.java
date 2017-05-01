package com.jmp2017w.customwebsite.web.converter;

import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToPersonConverter implements Converter<String, Person>
{
    @Autowired
    private PersonService personService;

    public Person convert(String idAsString)
    {
        Person person;
        try
        {
            Long id = Long.valueOf(idAsString);
            person = personService.findOne(id);
        }
        catch (Exception e)
        {
            person = null;
        }

        return person;
    }
}