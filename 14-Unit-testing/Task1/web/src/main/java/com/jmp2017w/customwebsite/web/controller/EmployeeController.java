package com.jmp2017w.customwebsite.web.controller;

import com.jmp2017w.customwebsite.domain.Address;
import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.domain.EmployeePersonalInfo;
import com.jmp2017w.customwebsite.domain.EmployeeStatus;
import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.domain.Unit;
import com.jmp2017w.customwebsite.service.EmployeeService;
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
import java.util.List;

/**
 * 
 */
@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UnitService unitService;

    @RequestMapping(value = "/employee/create", method = RequestMethod.GET)
    public String createEmployee(ModelMap model) throws ResourceNotFoundException
    {
        try
        {
            model.addAttribute("units", unitService.readAll());
            return "employee/create";
        }
        catch (ServiceOperationFailedException e)
        {
            throw new ResourceNotFoundException("Unable to display page for user.");
        }
    }

    @RequestMapping(value = "/employee/create", method = RequestMethod.POST)
    public String createEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                 @RequestParam("age") Integer age, @RequestParam("status") String status, @RequestParam("street") String street,
                                 @RequestParam("flatNumber") Integer flatNumber, @RequestParam("houseNumber") Integer houseNumber,
                                 @RequestParam("unit") Unit unit, ModelMap model) throws UserRequestProcessingFailedException
    {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        boolean isStatusSet = !(status == null || "".equals(status));
        employee.setStatus(isStatusSet ? EmployeeStatus.valueOf(status) : null);

        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();
        personalInfo.setAge(age);
        employee.setPersonalInfo(personalInfo);

        Address address = new Address();
        address.setStreet(street);
        address.setFlatNumber(flatNumber);
        address.setHouseNumber(houseNumber);
        employee.setAddress(address);

        employee.setUnit(unit);

        try
        {
            employeeService.create(employee);
            model.addAttribute("employees", employeeService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "employee/read";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String readAll(ModelMap model)
    {
        try
        {
            model.addAttribute("employees", employeeService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("employees", new LinkedList<Project>());
        }

        return "employee/read";
    }

    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.GET)
    public String updateEmployee(ModelMap model, @PathVariable("id") Employee employee) throws ResourceNotFoundException
    {
        if (employee == null || employee.getId() == null)
        {
            throw new ResourceNotFoundException("Unable to display page for user.");
        }

        try
        {
            model.addAttribute("employee", employee);
            model.addAttribute("units", unitService.readAll());

            List<EmployeeStatus> statuses = new LinkedList<EmployeeStatus>();
            statuses.add(EmployeeStatus.ACTIVE);
            statuses.add(EmployeeStatus.INACTIVE);
            model.addAttribute("statuses", statuses);
        }
        catch (ServiceOperationFailedException e)
        {
            throw new ResourceNotFoundException(e);
        }

        return "employee/update";
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.POST)
    public String updateEmployee(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName, @RequestParam("age") Integer age,
                                 @RequestParam("status") String status, @RequestParam("street") String street,
                                 @RequestParam("flatNumber") Integer flatNumber, @RequestParam("houseNumber") Integer houseNumber,
                                 @RequestParam("unit") Unit unit, ModelMap model) throws UserRequestProcessingFailedException
    {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        boolean isStatusSet = !(status == null || "".equals(status));
        employee.setStatus(isStatusSet ? EmployeeStatus.valueOf(status) : null);

        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();
        personalInfo.setAge(age);
        employee.setPersonalInfo(personalInfo);

        Address address = new Address();
        address.setStreet(street);
        address.setFlatNumber(flatNumber);
        address.setHouseNumber(houseNumber);
        employee.setAddress(address);

        employee.setUnit(unit);
        employee.setId(id);

        try
        {
            employeeService.update(employee);
            model.addAttribute("employees", employeeService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "employee/read";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
    public String deleteEmployee(ModelMap model)
    {
        try
        {
            model.addAttribute("employees", employeeService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("employees", new LinkedList<Project>());
        }
        return "employee/delete";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam Long[] ids, ModelMap model) throws UserRequestProcessingFailedException
    {
        try
        {
            employeeService.delete(ids);
            model.addAttribute("employees", employeeService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "employee/delete";
    }
}