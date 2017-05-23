package com.jmp2017w.customwebsite.web.converter;

import com.jmp2017w.customwebsite.domain.Unit;
import com.jmp2017w.customwebsite.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToUnitConverter implements Converter<String, Unit>
{
    @Autowired
    private UnitService unitService;

    public Unit convert(String idAsString)
    {
        Unit unit;
        try
        {
            Long id = Long.valueOf(idAsString);
            unit = unitService.read(id);
        }
        catch (Exception e)
        {
            unit = null;
        }

        return unit;
    }
}