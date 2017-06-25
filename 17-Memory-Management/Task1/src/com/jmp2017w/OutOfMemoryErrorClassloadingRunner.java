package com.jmp2017w;

import com.jmp2017w.bean.SomeClass;
import com.jmp2017w.bean.SomeClassFactory;

/**
 *
 */
public class OutOfMemoryErrorClassloadingRunner
{
    private static SomeClass someClass;

    public static void main(String... args)
    {
        try
        {
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                System.out.println("Iteration #" + i);
                someClass = SomeClassFactory.newInstance().copy(someClass);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}