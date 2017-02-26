package com.jmp2017w.service;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Service class which is responsible for getting plugins from specified folder.
 */
public class PluginManager
{
    private static Logger logger = Logger.getLogger(PluginManager.class);
    private List<Plugin> plugins;
    private String pathToPluginDirectory;


    /**
     * Creates <code>PluginManager</code> instance.
     * @param pluginDirectory relative path to directory with plugins
     */
    public PluginManager(String pluginDirectory)
    {
        this.pathToPluginDirectory = System.getProperty("user.dir") + File.separator + pluginDirectory;
    }

    /**
     * Gets loaded plugins.
     * @return {@link List<Plugin>} list of available plugins
     */
    public List<Plugin> getPlugins()
    {
        return plugins;
    }

    /**
     * Loads plugins from plugin directory.
     */
    public void loadPlugins()
    {
        plugins = new ArrayList<Plugin>();
        File pluginDirectory = new File(pathToPluginDirectory);
        if (pluginDirectory.exists())
        {
            File[] filesInPluginDirectory = pluginDirectory.listFiles();
            if (filesInPluginDirectory == null || filesInPluginDirectory.length == 0)
            {
                logger.info("There are no plugins in plugin directory!");
            }
            else
            {
                findJarFilesAndLoadPluginClasses(filesInPluginDirectory);
            }
        }
        else
        {
            logger.info("Plugin directory does not exist! Creating directory...");
            cretePluginDirectory(pluginDirectory);
        }
    }

    /**
     * Creates plugin directory.
     * @param pluginDirectory plugin directory
     */
    private void cretePluginDirectory(File pluginDirectory)
    {
        logger.info("Creating plugin directory...");
        boolean pluginDirectoryHasBeenCreated = pluginDirectory.mkdir();
        if (pluginDirectoryHasBeenCreated)
        {
            logger.info("Plugin directory has been created.");
        }
        else
        {
            logger.info("Unable to create plugin directory.");
        }
    }

    /**
     * Iterates files within plugin directory and loads only plugins.
     * @param filesInPluginDirectory files in plugin directory
     */
    private void findJarFilesAndLoadPluginClasses(File[] filesInPluginDirectory)
    {
        for (File file : filesInPluginDirectory)
        {
            if (!file.getName().toLowerCase().endsWith(".jar"))
            {
                continue;
            }

            try
            {
                PluginClassLoader loader = new PluginClassLoader(new URL[] {file.toURI().toURL()}, getClass().getClassLoader());

                JarFile jarPlugin = new JarFile(file);
                Enumeration<JarEntry> entries = jarPlugin.entries();
                while (entries.hasMoreElements())
                {
                    JarEntry element = entries.nextElement();
                    if (element.getName().endsWith(".class"))
                    {
                        loadPluginClasses(loader, element.getName());
                    }

                }

                loader.close();
                jarPlugin.close();
            }
            catch (IOException e)
            {
                logger.info("Error occurred while reloading plugins");
            }
        }
    }

    /**
     * Loads plugin classes.
     * @param classLoader class loader
     * @param pathToClass path to class within JAR-file.
     */
    private void loadPluginClasses(ClassLoader classLoader, String pathToClass)
    {
        try
        {
            String className = pathToClass.replaceAll(".class", "").replaceAll("/", ".");
            Class pluginClass = classLoader.loadClass(className);

            Class[] interfaces = pluginClass.getInterfaces();
            if (interfaces != null)
            {
                for (Class interfaceClass : interfaces)
                {
                    if ("com.jmp2017w.service.Plugin".equals(interfaceClass.getName()))
                    {
                        Constructor<Plugin> constructor = pluginClass.getConstructor();
                        plugins.add(constructor.newInstance());
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.info("Error occurred while loading plugin");
        }
    }
}