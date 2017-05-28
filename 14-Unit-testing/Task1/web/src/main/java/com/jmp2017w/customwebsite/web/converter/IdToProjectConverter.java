package com.jmp2017w.customwebsite.web.converter;

import com.jmp2017w.customwebsite.domain.Project;
import com.jmp2017w.customwebsite.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 */
public class IdToProjectConverter implements Converter<String, Project>
{
    @Autowired
    private ProjectService projectService;

    public Project convert(String idAsString)
    {
        Project project;
        try
        {
            Long id = Long.valueOf(idAsString);
            project = projectService.read(id);
        }
        catch (Exception e)
        {
            project = null;
        }

        return project;
    }
}