package com.jmp2017w.geometry.service;

import com.jmp2017w.geometry.figure.Figure;
import com.jmp2017w.geometry.figure.FigureType;

/**
 * This class responsible for prin logic of figures.
 */
public class FigurePrinter
{
    /**
     * Prints figure and performs some actions.
     * @param figure a figure.
     */
    public void print(Figure figure)
    {
        if (figure.getType() == FigureType.CONE)
        {
            System.out.println("Establishment of connection to the Mars");
            double area = figure.calculateArea();
            double volume = figure.calculateVolume();
            System.out.println("Connection is stable");
            System.out.println(String.format("area = %f, volume = %f", area, volume));
        }
        else if (figure.getType() == FigureType.SQUARE)
        {
            System.out.println("Establishment of connection to the Jupiter");
            double area = figure.calculateArea();
            System.out.println("Connection is stable");
            System.out.println(String.format("area = %f", area));
        }
    }
}