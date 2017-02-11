package com.jmp2017w.geometry.service;

import com.jmp2017w.geometry.figure.flat.Flipable;

/**
 * This class is responsible for flipping of figures.
 */
public class Flipper
{
    private Flipable figure;

    /**
     * Creates flipper.
     * @param figure a figure that can be flipped.
     */
    public Flipper(Flipable figure)
    {
        this.figure = figure;
    }

    /**
     * Flips the figure.
     */
    public void flip()
    {
        System.out.println("Checking whether the figure can be flipped...");
        System.out.println("Getting positive outcomes from space.");
        System.out.println("Starting to flip the figure...");
        figure.flip();
    }
}