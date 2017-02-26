package com.jmp2017w.service;

import org.apache.log4j.Logger;

/**
 * This class is responsible for saying 'Goodbye'.
 */
public class GoodbyePlugin implements Plugin
{
    private static Logger logger = Logger.getLogger(GoodbyePlugin.class);


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "Goodbye Sender";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription()
    {
        return "Prints 'Goodbye' message";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction()
    {
        logger.info("Goodbye");
    }
}