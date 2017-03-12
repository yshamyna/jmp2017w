package com.jmp2017w;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Write a program that will expose the work with java.util.List collections through pop() and push() methods.
 * Things to do:
 * 1) Write a class that wraps any given List collection.
 * 2) Implement two methods: Push – that appends a given object; Pop – pulls the last object from the collection.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        List<Double> doubleArrayList = new ArrayList<Double>();
        Stack<Double> doubleStack = new StackAdapter<Double>(doubleArrayList);
        Double[] doubleArray = {1.0, 2.0, 3.0, 4.0, 5.0};
        demonstrateAdapter(doubleStack, doubleArray);

        List<String> stringLinkedList = new LinkedList<String>();
        Stack<String> stringStack = new StackAdapter<String>(stringLinkedList);
        String[] stringArray = {"String", "Picture", "Flower", "Object", "Ice"};
        demonstrateAdapter(stringStack, stringArray);

        Stack<Integer> integerStack = new Stack<Integer>();
        Integer[] integerArray = {1, 2, 3, 4, 5};
        demonstrateAdapter(integerStack, integerArray);
    }

    /**
     * Demonstrates work of adapter.
     * @param stack either real <tt>Stack</tt> or wrapped <tt>List</tt> collection.
     * @param array array of items to fill the stack before actions
     */
    private static <E> void demonstrateAdapter(Stack<E> stack, E[] array)
    {
        System.out.println("\nArray size: " + array.length);
        System.out.println("Stack size: " + stack.size());
        for (E item : array)
        {
            System.out.println("Adding '" + item + "' to the stack");
            stack.push(item);
        }
        System.out.println("Stack size after adding elements from array: " + stack.size());

        while (stack.size() > 0)
        {
            E item = stack.pop();
            System.out.println("Taking '" + item + "' from the stack");
        }
        System.out.println("All objects from the stack were taken");

        try
        {
            stack.pop();
        }
        catch (EmptyStackException e)
        {
            System.out.println("Attempt to take another one element from the stack was unsuccessful");
        }
    }
}