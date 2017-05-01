package com.jmp2017w.customwebsite.dao;

import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.dao.exception.DataAccessException;
import com.jmp2017w.customwebsite.dao.exception.DataNotFoundException;

import java.util.List;

public interface PersonDAO
{
    List<Person> all() throws DataAccessException;
    Person getById(Long id) throws DataAccessException, DataNotFoundException;
    void add(Person person) throws DataAccessException;
    void update(Person person) throws DataAccessException;
    void delete(Person person) throws DataAccessException;
    void delete(List<Person> persons) throws DataAccessException;
}