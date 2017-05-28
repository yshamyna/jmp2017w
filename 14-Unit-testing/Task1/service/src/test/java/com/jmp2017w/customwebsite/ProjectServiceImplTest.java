package com.jmp2017w.customwebsite;

import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.service.ProjectService;
import com.jmp2017w.customwebsite.service.ProjectServiceImpl;
import com.jmp2017w.customwebsite.service.exception.InvalidInputParameterFoundException;
import org.junit.Test;

/**
 *
 */
public class ProjectServiceImplTest
{
    private ProjectService service = new ProjectServiceImpl();

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
        service.update(new Project());
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod3() throws Exception
    {
        Project project = new Project();
        project.setId(0L);
        service.update(project);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUpdateMethod4() throws Exception
    {
        Project project = new Project();
        project.setId(-1L);
        service.update(project);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInGetAssigneeMethod() throws Exception
    {
        service.getAssignees(null);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInAssignMethod() throws Exception
    {
        service.assign(null, null);
    }

    @Test(expected = InvalidInputParameterFoundException.class)
    public void testInvalidArgumentInUnassignMethod() throws Exception
    {
        service.unassign(null, null);
    }
}