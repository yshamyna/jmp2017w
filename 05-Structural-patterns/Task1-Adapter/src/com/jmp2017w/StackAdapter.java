package com.jmp2017w;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Wrapper for any given <tt>List</tt> collection.
 */
public class StackAdapter<E> extends Stack<E>
{
    private List<E> adaptee;

    /**
     * Public constructor.
     * @param adaptee a list to wrap
     * @exception IllegalArgumentException if parameter <code>adaptee</code> is <tt>NULL</tt>
     */
    public StackAdapter(List<E> adaptee)
    {
        if (adaptee == null)
        {
            throw new IllegalArgumentException();
        }
        this.adaptee = adaptee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E push(E item)
    {
        adaptee.add(item);
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized E pop()
    {
        int lastItemIndex = adaptee.size() - 1;
        if (lastItemIndex < 0)
        {
            throw new EmptyStackException();
        }

        E lastItem = adaptee.remove(lastItemIndex);
        return lastItem;
    }

    /**
     * Gets size of the stack.
     * @return element count
     */
    @Override
    public int size()
    {
        return adaptee.size();
    }
}