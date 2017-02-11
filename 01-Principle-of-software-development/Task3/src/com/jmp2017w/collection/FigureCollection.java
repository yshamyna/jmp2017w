package com.jmp2017w.collection;

import com.jmp2017w.geometry.figure.Figure;
import com.jmp2017w.geometry.figure.flat.FlatFigure;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is responsible for collecting flat figures.
 */
public class FigureCollection
{
    List<FlatFigure> figures = new ArrayList<FlatFigure>();

    /**
     * Adds figure to the collection.
     * @param item a figure
     */
    public void add(Figure item)
    {
        figures.add((FlatFigure) item);
        assert item instanceof FlatFigure;
    }

    /**
     * Removes a figure from the collection.
     * @param item a figure
     */
    public  void remove(Figure item)
    {
        figures.remove(item);
    }

    /**
     * Gets a figure from the collection by index.
     * @param index the index
     * @return a figure
     */
    public Figure get(int index)
    {
        return figures.get(index);
    }

    /**
     * Gets size of collection.
     * @return size
     */
    public int size()
    {
        return figures.size();
    }
}