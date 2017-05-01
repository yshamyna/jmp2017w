package com.jmp2017w.customwebsite.service;


import com.jmp2017w.customwebsite.bean.Person;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;

import java.util.List;

public interface PersonService
{
    List<Person> all() throws ServiceOperationFailedException;
    Person findOne(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
    void add(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
    void update(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
    void delete(Person person) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
    void delete(List<Person> persons) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
}
