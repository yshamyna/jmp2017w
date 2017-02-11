package com.jmp2017w.figure;

/**
 * Represents geometrical figure.
 */
public abstract class Figure implements VolumeCalculable
{
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract double calculateVolume();

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
}