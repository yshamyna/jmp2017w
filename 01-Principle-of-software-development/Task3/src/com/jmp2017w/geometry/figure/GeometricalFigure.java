package com.jmp2017w.geometry.figure;

/**
 * Provide methods for printing data of figure and calculating its area and volume.
 */
public interface GeometricalFigure
{
    /**
     * Prints data of figure.
     */
    void print();

    /**
     * Gets volume of figure.
     * @return volume
     */
    double calculateVolume();

    /**
     * Gets area of figure.
     * @return area
     */
    double calculateArea();
}