package com.jmp2017w.figure;

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
    public String toString()
    {
        return String.format("Cone: radius of base = %f, height = %f", radiusOfBase, heightOfCone);
    }
}