package com.jmp2017w;

import com.jmp2017w.figure.Cone;
import com.jmp2017w.figure.Cylinder;
import com.jmp2017w.figure.Ellipsoid;
import com.jmp2017w.figure.Figure;
import com.jmp2017w.figure.Parallelepiped;

/**
 * When calculating the volume of a complex figure, have to apply the KISS principle.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        Figure[] figures = {new Cone(4.4, 14), new Cylinder(2.3, 10), new Ellipsoid(2.3, 4, 6.7), new Parallelepiped(1, 2, 3)};
        for (Figure figure : figures)
        {
            System.out.println(figure + ", Volume = " + figure.calculateVolume());
        }
    }
}