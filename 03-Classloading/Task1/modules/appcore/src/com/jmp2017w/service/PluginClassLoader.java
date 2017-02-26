package com.jmp2017w.service;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.jar.JarFile;

/**
 * This class is responsible for loading plugins.
 */
public class PluginClassLoader extends URLClassLoader
{
    /**
     * {@inheritDoc}
     */
    public PluginClassLoader(URL[] urls, ClassLoader parent)
    {
        super(urls, parent);
    }

    /**
     * Closes all open by class loader jar files.
     * This method is used mainly to fix problem http://bugs.java.com/bugdatabase/view_bug.do?bug_id=5041014 for Java 6 and below.
     */
    public void close()
    {
        try
        {
            Class clazz = java.net.URLClassLoader.class;
            Field ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object sunMiscURLClassPath = ucp.get(this);
            Field loaders = sunMiscURLClassPath.getClass().getDeclaredField("loaders");
            loaders.setAccessible(true);
            Object collection = loaders.get(sunMiscURLClassPath);
            for (Object sunMiscURLClassPathJarLoader : ((Collection) collection).toArray())
            {
                try
                {
                    Field loader = sunMiscURLClassPathJarLoader.getClass().getDeclaredField("jar");
                    loader.setAccessible(true);
                    Object jarFile = loader.get(sunMiscURLClassPathJarLoader);
                    ((JarFile) jarFile).close();
                }
                catch (Throwable t)
                {
                    // if we got this far, this is probably not a JAR loader so skip it
                }
            }
        }
        catch (Throwable t)
        {
            // probably not a SUN VM
        }
    }
}