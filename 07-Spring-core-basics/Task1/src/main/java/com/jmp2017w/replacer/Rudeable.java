package com.jmp2017w.replacer;

import com.jmp2017w.bean.E;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Provides method to convert greeting message to rude one.
 */
public class Rudeable implements MethodReplacer
{
    /**
     * Reimplements method {@link E#greeting}
     */
    public Object reimplement(Object o, Method method, Object[] arguments) throws Throwable
    {
        System.out.println("Get out of here!");
        return o;
    }
}