package com.jmp2017w.customwebsite.web.controller;

import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.service.PersonService;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;
import com.jmp2017w.customwebsite.web.exception.ResourceNotFoundException;
import com.jmp2017w.customwebsite.web.exception.UserRequestProcessingFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class PersonController
{
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String allPersons(ModelMap model)
    {
        try
        {
            model.addAttribute("persons", personService.all());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("persons", new LinkedList<Person>());
        }

        return "person/read";
    }

    @RequestMapping(value = "/persons/edit/{id}", method = RequestMethod.GET)
    public String updatePerson(ModelMap model, @PathVariable("id") Person person) throws ResourceNotFoundException
    {
        if (person == null || person.getId() == null)
        {
            throw new ResourceNotFoundException("Unable to display page for user.");
        }

        model.addAttribute("person", person);
        return "person/update";
    }

    @RequestMapping(value = "/persons/edit", method = RequestMethod.POST)
    public String updatePerson(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName, @RequestParam("age") Long age,
                               @RequestParam("passportNumber") String passportNumber, ModelMap model) throws UserRequestProcessingFailedException
    {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setPassportNumber(passportNumber);

        try
        {
            personService.update(person);
            model.addAttribute("persons", personService.all());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "person/read";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String addPerson()
    {
        return "person/create";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String addPerson(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Long age,
                            @RequestParam String passportNumber, ModelMap model) throws UserRequestProcessingFailedException
    {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setPassportNumber(passportNumber);

        try
        {
            personService.add(person);
            model.addAttribute("persons", personService.all());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "person/read";
    }

    @RequestMapping(value = "/persons/delete", method = RequestMethod.GET)
    public String deletePersons(ModelMap model)
    {
        try
        {
            model.addAttribute("persons", personService.all());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("persons", new LinkedList<Person>());
        }
        return "person/delete";
    }

    @RequestMapping(value = "/persons/delete", method = RequestMethod.POST)
    public String deletePersons(@RequestParam Long[] ids, ModelMap model) throws UserRequestProcessingFailedException
    {
        List<Person> persons = new LinkedList<Person>();
        Person person;
        for (Long id : ids)
        {
            person = new Person();
            person.setId(id);
            persons.add(person);
        }

        try
        {
            personService.delete(persons);
            model.addAttribute("persons", personService.all());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "person/delete";
    }
}