package com.jmp2017w.customwebsite.service;

import com.jmp2017w.customwebsite.domain.Unit;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;

import java.util.List;

/**
 *
 */
public interface UnitService
{
    void create(Unit unit) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    Unit read(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    List<Unit> readAll() throws ServiceOperationFailedException;

    void delete(Long id) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void delete(Long[] ids) throws InvalidInputParameterFoundException, ServiceOperationFailedException;

    void update(Unit unit) throws InvalidInputParameterFoundException, ServiceOperationFailedException;
}