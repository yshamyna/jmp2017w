package com.jmp2017w.customwebsite.web.converter;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 */
public class IdToEmployeeConverter implements Converter<String, Employee>
{
    @Autowired
    private EmployeeService employeeService;

    public Employee convert(String idAsString)
    {
        Employee employee;
        try
        {
            Long id = Long.valueOf(idAsString);
            employee = employeeService.read(id);
        }
        catch (Exception e)
        {
            employee = null;
        }

        return employee;
    }
}