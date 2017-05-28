package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Unit;
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
public class UnitServiceImpl implements UnitService
{
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Unit unit) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (unit == null)
        {
            throw new InvalidInputParameterFoundException("Not enough information to save the entity: the entity is NULL.");
        }
        entityManager.persist(unit);
    }

    public Unit read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (id == null || id < 1L)
        {
            throw new InvalidInputParameterFoundException("Invalid value for ID found: " + id);
        }
        return entityManager.find(Unit.class, id);
    }

    public List<Unit> readAll() throws ServiceOperationFailedException
    {
        try
        {
            List<Unit> units = entityManager.createQuery("select u from Unit u", Unit.class).getResultList();
            if (units == null)
            {
                units = new LinkedList<Unit>();
            }
            return units;
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to read entities.", e);
        }
    }

    public void update(Unit unit) throws InvalidInputParameterFoundException, ServiceOperationFailedException
    {
        if (unit == null || unit.getId() == null || unit.getId() < 1L)
        {
            throw new InvalidInputParameterFoundException("Not enough information to update the entity: either the entity is NULL or its ID is invalid.");
        }

        try
        {
            entityManager.merge(unit);
        }
        catch (Exception e)
        {
            throw new ServiceOperationFailedException("Unable to update entity.", e);
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
            Unit entity = read(id);
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
                    Unit entity = read(id);
                    if (entity != null)
                    {
                        entityManager.remove(entity);
                    }
                }
            }
        }
    }
}