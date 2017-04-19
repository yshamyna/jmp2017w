package com.jmp2017w.db.dao;

import com.jmp2017w.bean.Person;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface PersonDAO
{
    List<Person> all();
    Person get(Long id);
    void add(Person person);
    void remove(Long id);
    void remove(Set<Long> ids);
    void edit(Person person);
}
