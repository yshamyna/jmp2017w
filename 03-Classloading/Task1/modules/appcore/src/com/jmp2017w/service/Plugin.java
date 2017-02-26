package com.jmp2017w.service;

/**
 * Provides common plugin methods for the application needs.
 */
public interface Plugin
{
    /**
     * Gets name of the plugin.
     * @return {@link String} plugin name
     */
    String getName();

    /**
     * Gets description of the plugin.
     * @return {@link String} plugin description
     */
    String getDescription();

    /**
     * Executes the plugin.
     */
    void doAction();
}