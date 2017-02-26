package com.jmp2017w.service;

import org.apache.log4j.Logger;

/**
 * This class is responsible for saying 'How are you?'.
 */
public class HowAreYouPlugin implements Plugin
{
    private static Logger logger = Logger.getLogger(HowAreYouPlugin.class);


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "How Are You Sender";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription()
    {
        return "Prints 'How are you?' message";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction()
    {
        logger.info("How are you?");
    }
}