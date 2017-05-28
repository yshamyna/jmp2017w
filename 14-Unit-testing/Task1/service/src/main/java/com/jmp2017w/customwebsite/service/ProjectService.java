package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public interface ProjectService
{
    void create(Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    Project read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    List<Project> readAll() throws ServiceOperationFailedException;

    void delete(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void delete(Long[] ids) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void update(Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    Collection<Employee> getAssignees(Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void assign(Employee employee, Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void unassign(Employee employee, Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
}