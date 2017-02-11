package com.jmp2017w.geometry.figure.flat;

/**
 * Represents square that can be flipped.
 */
public class FlipableSquare extends Square
{
    /**
     * Creates flipable square.
     * @param size
     */
    public FlipableSquare(double size)
    {
        super(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flip()
    {
        System.out.println("Flipping...");
    }
}