package com.jmp2017w.figure;

/**
 * Represents cylinder.
 */
public class Cylinder extends Figure
{
    double radiusOfBase;
    double heightOfCylinder;

    /**
     * Creates cone.
     * @param radiusOfBase radius of base
     * @param heightOfCylinder height of cylinder
     * @throws IllegalStateException if any length is less or equals to zero
     */
    public Cylinder(double radiusOfBase, double heightOfCylinder)
    {
        throwExceptionIfAnyDataIsIncorrect(radiusOfBase, heightOfCylinder);
        this.radiusOfBase = radiusOfBase;
        this.heightOfCylinder = heightOfCylinder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateVolume()
    {
        return Math.PI * Math.pow(radiusOfBase, 2) * heightOfCylinder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("Cylinder: radius of base = %f, height = %f", radiusOfBase, heightOfCylinder);
    }
}