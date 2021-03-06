package com.jmp2017w.customwebsite.web.listener;


import com.jmp2017w.customwebsite.db.DBHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Adds data to DB for the web application:
 * - creates DB schema;
 * - populates created DB with default data.
 */
public class ApplicationContextListener implements ServletContextListener
{
    /**
     * {@inheritDoc}
     */
    public void contextInitialized(ServletContextEvent sce)
    {
        DBHelper dbHelper = new DBHelper();
        dbHelper.initializeDatabase();
    }

    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent sce)
    {
        // nothing
    }
}