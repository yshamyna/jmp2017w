package com.jmp2017w;

import com.jmp2017w.collection.FigureCollection;
import com.jmp2017w.geometry.figure.Figure;
import com.jmp2017w.geometry.figure.flat.FlipableSquare;
import com.jmp2017w.geometry.figure.flat.Square;
import com.jmp2017w.geometry.figure.volume.Cone;
import com.jmp2017w.geometry.service.Flipper;

/**
 * Mentee has to implement examples of erroneous application with using SOLID principles.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        /*
         * The collection does not meet "Liskov substitution principle".
         */
        FigureCollection collection = new FigureCollection();
        for (int size = 1; size < 5; size++)
        {
            collection.add(new Square(size));
        }

        /*
         * Class Figure does not meet "Single-responsiblity principle".
         */
        Figure cone = new Cone(2.2, 12);
        Figure square = collection.get(0);

        /*
         * 1) Method FigurePrinter#print(Figure) does not meet "Open-closed principle".
         * 2) Method 'print' in classes that represent figures does not meet "Dependency Inversion Principle".
         */
        cone.print();
        square.print();

        /*
         * Class FlipableSquare does not meet "Interface segregation principle".
         */
        FlipableSquare flipableSquare = new FlipableSquare(12);
        Flipper flipper = new Flipper(flipableSquare);
        flipper.flip();
    }
}