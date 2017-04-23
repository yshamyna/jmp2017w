package com.jmp2017w.service;

import com.jmp2017w.bean.Person;

import java.util.List;
import java.util.Set;

public interface PersonService
{
    List<Person> all();
    Person get(Long id);
    void add(Person person);
    void remove(Long id);
    void remove(Set<Long> ids);
    void edit(Person person);
}
