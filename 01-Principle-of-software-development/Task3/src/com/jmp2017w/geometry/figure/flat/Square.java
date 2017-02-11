package com.jmp2017w.geometry.figure.flat;

import com.jmp2017w.geometry.figure.FigureType;
import com.jmp2017w.geometry.service.FigurePrinter;

/**
 * Represents square.
 */
public class Square extends FlatFigure implements Flipable
{
    double size;

    /**
     * Creates square.
     * @param size size of square.
     * @throws IllegalStateException if any length is less or equals to zero
     */
    public Square(double size)
    {
        super(FigureType.SQUARE);
        throwExceptionIfAnyDataIsIncorrect(size);
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print()
    {
        System.out.println(String.format("Square: size = %f", size));
        FigurePrinter printer = new FigurePrinter();
        printer.print(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateArea()
    {
        return Math.pow(size, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flip()
    {
       // nothing
    }
}