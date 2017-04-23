package com.jmp2017w.service;

import com.jmp2017w.bean.Person;
import com.jmp2017w.db.dao.PersonDAO;
import com.jmp2017w.db.dao.PersonDAOImpl;

import java.util.List;
import java.util.Set;

public class PersonServiceImpl implements PersonService
{
    private PersonDAO personDAO = new PersonDAOImpl();

    public List<Person> all()
    {
        return personDAO.all();
    }

    public Person get(Long id)
    {
        return personDAO.get(id);
    }

    public void add(Person person)
    {
        personDAO.add(person);
    }

    public void remove(Long id)
    {
        personDAO.remove(id);
    }

    public void remove(Set<Long> ids)
    {
        personDAO.remove(ids);
    }

    public void edit(Person person)
    {
        personDAO.edit(person);
    }
}