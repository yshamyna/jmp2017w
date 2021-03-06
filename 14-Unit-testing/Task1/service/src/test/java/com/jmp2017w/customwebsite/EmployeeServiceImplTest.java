package com.jmp2017w.customwebsite;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.service.EmployeeService;
import com.jmp2017w.customwebsite.service.EmployeeServiceImpl;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import org.junit.Test;


/**
 *
 */
public class EmployeeServiceImplTest
{
    private EmployeeService service = new EmployeeServiceImpl();

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInCreateMethod() throws Exception
    {
        service.create(null);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInReadMethod1() throws Exception
    {
        service.read(null);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInReadMethod2() throws Exception
    {
        service.read(0L);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInReadMethod3() throws Exception
    {
        service.read(-1L);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInDeleteMethod1() throws Exception
    {
        Long id = null;
        service.delete(id);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInDeleteMethod2() throws Exception
    {
        service.delete(0L);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInDeleteMethod3() throws Exception
    {
        service.delete(-1L);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod1() throws Exception
    {
        service.update(null);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod2() throws Exception
    {
        service.update(new Employee());
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod3() throws Exception
    {
        Employee employee = new Employee();
        employee.setId(0L);
        service.update(employee);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod4() throws Exception
    {
        Employee employee = new Employee();
        employee.setId(-1L);
        service.update(employee);
    }
}