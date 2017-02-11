package com.jmp2017w.geometry.figure.volume;

import com.jmp2017w.geometry.figure.Figure;
import com.jmp2017w.geometry.figure.FigureType;
import com.jmp2017w.geometry.service.FigurePrinter;

/**
 * Represents cone.
 */
public class Cone extends Figure
{
    /** This constant is used while calculating volume of cone. */
    private static final double VOLUME_CONSTANT = (double) 1 / 3;

    double radiusOfBase;
    double heightOfCone;

    /**
     * Creates cone.
     * @param radiusOfBase radius of base
     * @param heightOfCone height of cone
     * @throws IllegalStateException if any length is less or equals to zero
     */
    public Cone(double radiusOfBase, double heightOfCone)
    {
        super(FigureType.CONE);
        throwExceptionIfAnyDataIsIncorrect(radiusOfBase, heightOfCone);
        this.radiusOfBase = radiusOfBase;
        this.heightOfCone = heightOfCone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateVolume()
    {
        return VOLUME_CONSTANT * Math.PI * Math.pow(radiusOfBase, 2) * heightOfCone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateArea()
    {
        double coneGeneratrix = Math.sqrt(Math.pow(radiusOfBase, 2) + Math.pow(heightOfCone, 2));
        return Math.PI * radiusOfBase * coneGeneratrix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("Cone: radius of base = %f, height = %f", radiusOfBase, heightOfCone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print()
    {
        System.out.println(String.format("Cone: radius of base = %f, height = %f", radiusOfBase, heightOfCone));
        FigurePrinter printer = new FigurePrinter();
        printer.print(this);
    }
}