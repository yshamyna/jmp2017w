package com.jmp2017w.figure;

/**
 * Represents ellipsoid.
 */
public class Ellipsoid extends Figure
{
    /** This constant is used while calculating volume of ellipsoid. */
    private static final double VOLUME_CONSTANT = (double) 4 / 3;

    double halfAxleA;
    double halfAxleB;
    double halfAxleC;

    /**
     * Creates ellipsoid.
     * @param halfAxleA first half-axle
     * @param halfAxleB second half-axle
     * @param halfAxleC third half-axle
     * @throws IllegalStateException if any length is less or equals to zero
     */
    public Ellipsoid(double halfAxleA, double halfAxleB, double halfAxleC)
    {
        throwExceptionIfAnyDataIsIncorrect(halfAxleA, halfAxleB, halfAxleC);
        this.halfAxleA = halfAxleA;
        this.halfAxleB = halfAxleB;
        this.halfAxleC = halfAxleC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateVolume()
    {
        return VOLUME_CONSTANT * Math.PI * halfAxleA * halfAxleB * halfAxleC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("Ellipsoid: first half-axle = %f, second half-axle = %f, third half-axle = %f",
                halfAxleA, halfAxleB, halfAxleC);
    }
}