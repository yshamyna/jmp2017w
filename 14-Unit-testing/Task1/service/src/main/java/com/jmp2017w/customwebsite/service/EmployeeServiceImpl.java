package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Repository
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Employee employee) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (employee == null)
        {
            throw new InvalidInputParameterFoundException("Not enough information to save the entity: the entity is NULL.");
        }
        entityManager.persist(employee);
    }

    public Employee read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (id == null || id < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + id);
        }

        return entityManager.find(Employee.class, id);
    }

    public List<Employee> readAll() throws ServiceOperationFailedException
    {
        try
        {
            List<Employee> employees = entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
            if (employees == null)
            {
                employees = new LinkedList<Employee>();
            }
            return employees;
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to read entities.", e);
        }
    }

    public void delete(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (id == null || id < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + id);
        }

        try
        {
            Employee entity = read(id);
            if (entity != null)
            {
                entityManager.remove(entity);
            }
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to remove entity.", e);
        }
    }

    public void delete(Long[] ids) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                if (id != null && id > 0)
                {
                    Employee entity = read(id);
                    if (entity != null)
                    {
                        entityManager.remove(entity);
                    }
                }
            }
        }
    }

    public void update(Employee employee) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (employee == null || employee.getId() == null || employee.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Not enough information to update the entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            entityManager.merge(employee);
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to update entity.", e);
        }
    }
}