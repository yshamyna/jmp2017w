package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.dao.PersonDAO;
import com.jmp2017w.customwebsite.dao.exception.DataAccessException;
import com.jmp2017w.customwebsite.dao.exception.DataNotFoundException;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService
{
    @Autowired
    private PersonDAO personDAO;

    public List<Person> all() throws ServiceOperationFailedException
    {
        try
        {
            return personDAO.all();
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to retrieve all persons.", e);
        }
    }

    public Person findOne(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (id == null || id < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + id);
        }

        Person person = null;
        try
        {
            person = personDAO.getById(id);
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to retrieve a person by ID '" + id + "'.", e);
        }
        catch (DataNotFoundException e)
        {
            person = new Person();
        }

        return person;
    }

    public void add(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (person == null)
        {
            throw new InvalidInputParameterFoundException("A person to add is NULL.");
        }

        try
        {
            personDAO.add(person);
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to add a person.", e);
        }
    }

    public void update(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (person == null || person.getId() == null)
        {
            throw new InvalidInputParameterFoundException("Either a person to update is NULL or its ID is NULL.");
        }
        if (person.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + person.getId());
        }

        try
        {
            personDAO.update(person);
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to update a person.", e);
        }
    }

    public void delete(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (person == null || person.getId() == null)
        {
            throw new InvalidInputParameterFoundException("Either person to delete is NULL or its ID is NULL.");
        }
        if (person.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + person.getId());
        }

        try
        {
            personDAO.delete(person);
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to delete a person.", e);
        }
    }

    public void delete(List<Person> persons) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (persons == null || persons.size() == 0)
        {
            throw new InvalidInputParameterFoundException("Persons to delete are absent.");
        }

        for (Person person : persons)
        {
            if (person == null || person.getId() == null)
            {
                throw new InvalidInputParameterFoundException("Either person to delete is NULL or its ID is NULL.");
            }
            if (person.getId() < 1L)
            {
                throw new InvalidInputParameterFoundException("Invalid value for ID found: " + person.getId());
            }
        }

        try
        {
            personDAO.delete(persons);
        }
        catch (DataAccessException e)
        {
            throw new ServiceOperationFailedException("Unable to delete persons.", e);
        }
    }
}