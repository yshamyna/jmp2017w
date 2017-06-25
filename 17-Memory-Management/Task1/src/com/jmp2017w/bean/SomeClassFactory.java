package com.jmp2017w.bean;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 */
public class SomeClassFactory
{
    public static SomeClass newInstance()
    {
        try
        {
            return loadClassAndReturnInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getCause());
        }
    }

    private static SomeClass loadClassAndReturnInstance() throws Exception
    {
        URLClassLoader tmp = new URLClassLoader(new URL[] {getClassPath()})
            {
                public Class<?> loadClass(String name) throws ClassNotFoundException
                {
                    if ("com.jmp2017w.bean.SomeClassImpl".equals(name) || "com.jmp2017w.bean.LeakImpl".equals(name))
                    {
                        return findClass(name);
                    }
                    return super.loadClass(name);
                }
            };

        return (SomeClass) tmp.loadClass("com.jmp2017w.bean.SomeClassImpl").newInstance();
    }

    private static URL getClassPath()
    {
        String resName =  SomeClassFactory.class.getName().replace('.', '/') + ".class";
        String loc = SomeClassFactory.class.getClassLoader().getResource(resName).toExternalForm();
        String dir = loc.substring(0, loc.length() - resName.length());

        try
        {
            return new URL(dir);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}