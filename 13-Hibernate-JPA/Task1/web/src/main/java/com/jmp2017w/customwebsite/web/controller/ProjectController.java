package com.jmp2017w.customwebsite.web.controller;

import com.jmp2017w.customwebsite.domain.Employee;
import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.service.EmployeeService;
import com.jmp2017w.customwebsite.service.ProjectService;
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
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/project/create", method = RequestMethod.GET)
    public String createProject()
    {
        return "project/create";
    }

    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    public String createProject(@RequestParam String name, ModelMap model) throws UserRequestProcessingFailedException
    {
        Project Project = new Project();
        Project.setName(name);

        try
        {
            projectService.create(Project);
            model.addAttribute("projects", projectService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "project/read";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String readAll(ModelMap model)
    {
        try
        {
            model.addAttribute("projects", projectService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("projects", new LinkedList<Project>());
        }

        return "project/read";
    }

    @RequestMapping(value = "/project/update/{id}", method = RequestMethod.GET)
    public String updateProject(ModelMap model, @PathVariable("id") Project project) throws ResourceNotFoundException, UserRequestProcessingFailedException
    {
        if (project == null || project.getId() == null)
        {
            throw new ResourceNotFoundException("Unable to display page for user.");
        }

        model.addAttribute("project", project);
        try
        {
            model.addAttribute("assignedEmployees", projectService.getAssignees(project));
            model.addAttribute("allEmployees", employeeService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }
        return "project/update";
    }

    @RequestMapping(value = "/project/update", method = RequestMethod.POST)
    public String updateProject(@RequestParam("id") Long id, @RequestParam("name") String name,
                             ModelMap model) throws UserRequestProcessingFailedException
    {
        Project project = new Project();
        project.setName(name);
        project.setId(id);

        try
        {
            projectService.update(project);
            model.addAttribute("projects", projectService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "project/read";
    }

    @RequestMapping(value = "/project/assign", method = RequestMethod.POST)
    public String assignToProject(@RequestParam("id") Project project, @RequestParam("assignedEmployee") Employee employee,
                                  ModelMap model) throws UserRequestProcessingFailedException, ResourceNotFoundException
    {
        try
        {
            projectService.assign(employee, project);
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        model.addAttribute("redirectTo", "/project/update/" + project.getId());
        return "redirect";
    }

    @RequestMapping(value = "/project/unassign", method = RequestMethod.POST)
    public String unassignFromProject(@RequestParam("id") Project project, @RequestParam("unassignedEmployee") Employee employee,
                                      ModelMap model) throws UserRequestProcessingFailedException, ResourceNotFoundException
    {
        try
        {
            projectService.unassign(employee, project);
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        model.addAttribute("redirectTo", "/project/update/" + project.getId());
        return "redirect";
    }

    @RequestMapping(value = "/project/delete", method = RequestMethod.GET)
    public String deleteProject(ModelMap model)
    {
        try
        {
            model.addAttribute("projects", projectService.readAll());
        }
        catch (ServiceOperationFailedException e)
        {
            model.addAttribute("projects", new LinkedList<Project>());
        }
        return "project/delete";
    }

    @RequestMapping(value = "/project/delete", method = RequestMethod.POST)
    public String deleteProject(@RequestParam Long[] ids, ModelMap model) throws UserRequestProcessingFailedException
    {
        try
        {
            projectService.delete(ids);
            model.addAttribute("projects", projectService.readAll());
        }
        catch (InvalidInputParameterFoundException e)
        {
            // nothing
        }
        catch (ServiceOperationFailedException e)
        {
            throw new UserRequestProcessingFailedException(e);
        }

        return "project/delete";
    }
}
