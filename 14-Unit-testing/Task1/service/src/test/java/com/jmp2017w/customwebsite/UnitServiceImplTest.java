package com.jmp2017w.customwebsite;

import com.jmp2017w.customwebsite.domain.Unit;
import com.jmp2017w.customwebsite.service.UnitService;
import com.jmp2017w.customwebsite.service.UnitServiceImpl;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import org.junit.Test;

/**
 *
 */
public class UnitServiceImplTest
{
    private UnitService service = new UnitServiceImpl();

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
        service.update(new Unit());
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod3() throws Exception
    {
        Unit unit = new Unit();
        unit.setId(0L);
        service.update(unit);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod4() throws Exception
    {
        Unit unit = new Unit();
        unit.setId(-1L);
        service.update(unit);
    }
}
