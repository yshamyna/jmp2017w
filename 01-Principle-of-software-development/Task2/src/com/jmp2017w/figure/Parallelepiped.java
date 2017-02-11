package com.jmp2017w.figure;

/**
 * Represents parallelepiped.
 */
public class Parallelepiped extends Figure
{
    double widthOfBase;
    double heightOfBase;
    double heightOfParallelepiped;

    /**
     * Creates parallelepiped.
     * @param widthOfBase width of base
     * @param heightOfBase height of base
     * @param heightOfParallelepiped height of parallelepiped
     * @throws IllegalStateException if any length is less or equals to zero
     */
    public Parallelepiped(double widthOfBase, double heightOfBase, double heightOfParallelepiped)
    {
        throwExceptionIfAnyDataIsIncorrect(widthOfBase, heightOfBase, heightOfParallelepiped);
        this.widthOfBase = widthOfBase;
        this.heightOfBase = heightOfBase;
        this.heightOfParallelepiped = heightOfParallelepiped;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateVolume()
    {
        return widthOfBase * heightOfBase * heightOfParallelepiped;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("Parallelepiped: width of base = %f, height of base= %f, height of parallelepiped = %f",
                widthOfBase, heightOfBase, heightOfParallelepiped);
    }
}