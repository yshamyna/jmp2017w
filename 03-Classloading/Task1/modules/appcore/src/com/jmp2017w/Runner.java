package com.jmp2017w;

import com.jmp2017w.service.Plugin;
import com.jmp2017w.service.PluginManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Create console application for dynamic loading of new development functionality.
 * New functionality should be placed in specified directory and should be archived in jar.
 * The application should have console menu for choosing option, the output should be done through usage of log4j library.
 */
public class Runner
{
    /** Count of system command, includes 'Reload plugins' and 'Exit' commands. */
    private static final Integer COUNT_OF_SYSTEM_COMMAND = 2;
    /** Code for reloading classpath. */
    private static final int RELOAD_PLUGINS_COMMAND_CODE = 1;
    /** Code for exiting from the application. */
    private static final int EXIT_COMMAND_CODE = 2;

    private static PluginManager pluginManager = new PluginManager("plugins");
    private static Logger logger = Logger.getLogger(Runner.class);


    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String... args)
    {
        pluginManager.loadPlugins();
        List<Plugin> plugins;
        String inputMessageFromUser;

        while (true)
        {
            try
            {
                plugins = pluginManager.getPlugins();
                printMenu(plugins);
                inputMessageFromUser = readInputMessageFromUser();
                processInputData(inputMessageFromUser, plugins);
            }
            catch (NumberFormatException numberFormatException)
            {
                logger.info("Entered 'item number' is not a number!");
            }
            catch (IllegalStateException illegalStateException)
            {
                logger.info("Chosen option does not exist in the menu!");
            }
        }
    }

    /**
     * Prints menu on console view.
     * @param availablePlugins a list of available plugins
     */
    private static void printMenu(List<Plugin> availablePlugins)
    {
        int itemNumber = 1;
        if (availablePlugins != null)
        {
            String menuMessage;
            for (Plugin plugin : availablePlugins)
            {
                menuMessage = String.format("%d) [PLUGIN] %s. %s", itemNumber++, plugin.getName(), plugin.getDescription());
                logger.info(menuMessage);
            }
        }

        logger.info(String.format("%d) %s", itemNumber++, "Reload plugins"));
        logger.info(String.format("%d) %s", itemNumber, "Exit"));
        logger.info("Enter item number: ");
    }

    /**
     * Gets message from console.
     * @return {@link String} input message
     */
    private static String readInputMessageFromUser()
    {
        return System.console().readLine();
    }

    /**
     * Handles user's input data: either runs plugin or performs system command.
     * @param inputData menu item number
     * @param availablePlugins list of available plugins
     * @throws NumberFormatException in case if item number is not a number
     * @throws IllegalStateException in case if specified item number does not correspond to existing menu
     */
    private static void processInputData(String inputData, List<Plugin> availablePlugins) throws NumberFormatException,
            IllegalStateException
    {
        Integer menuItemNumber = Integer.valueOf(inputData);
        Integer pluginCount = availablePlugins == null ? 0 : availablePlugins.size();
        if (menuItemNumber < 1 || menuItemNumber > COUNT_OF_SYSTEM_COMMAND + pluginCount)
        {
            throw new IllegalStateException();
        }

        if (menuItemNumber > pluginCount)
        {
            runSystemCommand(menuItemNumber - pluginCount);
        }
        else
        {
            runPlugin(menuItemNumber - 1, availablePlugins);
        }
    }

    /**
     * Runs system command.
     * @param commandCode command code. Allowable values are: 1 - means classpath reloading, 2 - means exiting from the application
     */
    private static void runSystemCommand(Integer commandCode)
    {
        switch (commandCode)
        {
            case RELOAD_PLUGINS_COMMAND_CODE:
                logger.info("Reloading...");
                pluginManager.loadPlugins();
                break;
            case EXIT_COMMAND_CODE:
                System.exit(0);
                break;
            default:
                logger.info("Unsupported system command code: " + commandCode);
        }
    }

    /**
     * Runs plugin.
     * @param pluginNumber number of plugin
     * @param availablePlugins list of all available plugins
     */
    private static void runPlugin(Integer pluginNumber, List<Plugin> availablePlugins)
    {
        Plugin plugin = availablePlugins.get(pluginNumber);
        logger.info("Running plugin " + plugin.getName());
        plugin.doAction();
    }
}