package com.jmp2017w.customwebsite.web.controller;

import com.jmp2017w.customwebsite.domain.Unit;
import com.jmp2017w.customwebsite.service.UnitService;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import com.jmp2017w.customwebsite.service.exception.ServiceOperationFailedException;
import com.jmp2017w.customwebsite.web.exception.ResourceNotFoundException;
import com.jmp2017w.customwebsite.web.exception.UserRequestProcessingFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;

/**
 *
 */
@Controller
public class UnitController
{
    @Autowired
    private UnitService unitService;

    @RequestMapping(value = "/unit/create", method = RequestMethod.GET)
    public String createUnit()
    {
        return "unit/create";
    }

    @RequestMapping(value = "/unit/create", method = RequestMethod.POST)
    public String createUnit(@RequestParam String name, ModelMap model) throws UserRequestProcessingFailedException
    {
        Unit unit = new Unit();
        unit.setName(name);

        try
        {
            unitService.create(unit);
            model.addAttribute("units", unitService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "unit/read";
    }

    @RequestMapping(value = "/unit", method = RequestMethod.GET)
    public String readAll(ModelMap model)
    {
        try
        {
            model.addAttribute("units", unitService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("units", new LinkedList<Unit>());
        }

        return "unit/read";
    }

    @RequestMapping(value = "/unit/update/{id}", method = RequestMethod.GET)
    public String updateUnit(ModelMap model, @PathVariable("id") Unit unit) throws ResourceNotFoundException
    {
        if (unit == null || unit.getId() == null)
        {
            throw new ResourceNotFoundException("Unable to display page for user.");
        }

        model.addAttribute("unit", unit);
        return "unit/update";
    }

    @RequestMapping(value = "/unit/update", method = RequestMethod.POST)
    public String updateUnit(@RequestParam("id") Long id, @RequestParam("name") String name,
                               ModelMap model) throws UserRequestProcessingFailedException
    {
        Unit unit = new Unit();
        unit.setName(name);
        unit.setId(id);

        try
        {
            unitService.update(unit);
            model.addAttribute("units", unitService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "unit/read";
    }

    @RequestMapping(value = "/unit/delete", method = RequestMethod.GET)
    public String deleteUnit(ModelMap model)
    {
        try
        {
            model.addAttribute("units", unitService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("units", new LinkedList<Unit>());
        }
        return "unit/delete";
    }

    @RequestMapping(value = "/unit/delete", method = RequestMethod.POST)
    public String deleteUnit(@RequestParam Long[] ids, ModelMap model) throws UserRequestProcessingFailedException
    {
        try
        {
            unitService.delete(ids);
            model.addAttribute("units", unitService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "unit/delete";
    }
}