package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;

import java.util.List;

/**
 *
 */
public interface EmployeeService
{
    void create(Employee employee) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    Employee read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    List<Employee> readAll() throws ServiceOperationFailedException;

    void delete(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void delete(Long[] ids) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void update(Employee employee) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
}
