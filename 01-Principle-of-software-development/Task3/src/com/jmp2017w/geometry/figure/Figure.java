package com.jmp2017w.geometry.figure;

/**
 * Base class for all figures.
 */
public abstract class Figure implements GeometricalFigure
{
    protected FigureType type;

    /**
     * Stores information about type of figure.
     * @param type
     */
    protected Figure(FigureType type)
    {
        this.type = type;
    }

    /**
     * Gets type of figure.
     * @return type
     */
    public FigureType getType()
    {
        return type;
    }

    /**
     * Checks whether or length are correct for building geometrical figure.
     * @param lengthsToValidate array of lengths
     * @throws IllegalStateException if at least one length less or equals to zero
     */
    protected void throwExceptionIfAnyDataIsIncorrect(double... lengthsToValidate)
    {
        for (double length : lengthsToValidate)
        {
            if (length <= 0)
            {
                throw new IllegalStateException("Incorrect length found: " + length);
            }
        }
    }

    /**
     * Prints data of the figure.
     */
    public abstract void print();

    /**
     * Gets volume of the figure.
     * @return volume
     */
    public abstract double calculateVolume();

    /**
     * Gets area of the figure.
     * @return area
     */
    public abstract double calculateArea();
}