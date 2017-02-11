package com.jmp2017w.geometry.figure.flat;

import com.jmp2017w.geometry.figure.Figure;
import com.jmp2017w.geometry.figure.FigureType;

/**
 * Common class for all flat geometrical figures.
 */
public abstract class FlatFigure extends Figure
{
    /**
     * Stores information about type of figure.
     * @param type
     */
    protected FlatFigure(FigureType type)
    {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateVolume()
    {
        return 0;
    }
}