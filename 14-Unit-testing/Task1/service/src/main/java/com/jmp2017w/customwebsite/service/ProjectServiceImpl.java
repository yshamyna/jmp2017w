package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Repository
@Transactional
public class ProjectServiceImpl implements ProjectService
{
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Project entity) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (entity == null)
        {
            throw new InvalidInputParameterFoundException("Not enough information to save the entity: the entity is NULL.");
        }
        entityManager.persist(entity);
    }

    public Project read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (id == null || id < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + id);
        }

        return entityManager.find(Project.class, id);
    }

    public List<Project> readAll() throws ServiceOperationFailedException
    {
        try
        {
            List<Project> projects = entityManager.createQuery("select p from Project p", Project.class).getResultList();
            if (projects == null)
            {
                projects = new LinkedList<Project>();
            }
            return projects;
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
            Project entity = read(id);
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
                    Project entity = read(id);
                    if (entity != null)
                    {
                        entityManager.remove(entity);
                    }
                }
            }
        }
    }

    public void update(Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (project == null || project.getId() == null || project.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Not enough information to update the entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            entityManager.merge(project);
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to update entity.", e);
        }
    }

    public Collection<Employee> getAssignees(Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (project == null || project.getId() == null || project.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            Collection<Employee> employees = entityManager.createQuery("select p.employees from Project p WHERE p = ?1")
                    .setParameter(1, project).getResultList();
            return employees;
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to read entities.", e);
        }
    }

    public void assign(Employee employee, Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (project == null || project.getId() == null || project.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid Project entity: either the entity is NULL or its ID is invalid.");
        }
        if (employee == null || employee.getId() == null || employee.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid Employee entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            entityManager.createNativeQuery("SELECT id from employeeProjectCorrelation where employeeID = ?1 and projectID = ?2")
                    .setParameter(1, employee.getId()).setParameter(2, project.getId()).getSingleResult();
        }
        catch (NoResultException e)
        {
            entityManager.createNativeQuery("INSERT INTO employeeProjectCorrelation(employeeID, projectID) VALUES (?1, ?2)")
                    .setParameter(1, employee.getId()).setParameter(2, project.getId()).executeUpdate();
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to insert entities.", e);
        }
    }

    public void unassign(Employee employee, Project project) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (project == null || project.getId() == null || project.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid Project entity: either the entity is NULL or its ID is invalid.");
        }
        if (employee == null || employee.getId() == null || employee.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid Employee entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            Object id = entityManager.createNativeQuery("SELECT id from employeeProjectCorrelation where employeeID = ?1 and projectID = ?2")
                    .setParameter(1, employee.getId()).setParameter(2, project.getId()).getSingleResult();
            if (id != null)
            {
                entityManager.createNativeQuery("DELETE FROM employeeProjectCorrelation WHERE id = ?1")
                        .setParameter(1, id).executeUpdate();
            }
        }
        catch (NoResultException e)
        {
            // nothing
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to delete entities.", e);
        }
    }
}