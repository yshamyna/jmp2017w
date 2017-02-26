package com.jmp2017w.service;

import org.apache.log4j.Logger;

/**
 * This class is responsible for saying 'Hello'.
 */
public class HelloPlugin implements Plugin
{
    private static Logger logger = Logger.getLogger(HelloPlugin.class);


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "Hello Sender";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription()
    {
        return "Prints 'Hello' message";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction()
    {
        logger.info("Hello");
    }
}