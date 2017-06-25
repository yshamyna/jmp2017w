package com.jmp2017w.bean;

/**
 *
 */
public final class Bean
{
    private Bean bean;

    public Bean()
    {
    }

    public Bean(Bean bean)
    {
        this.bean = new Bean(bean);
    }

    public Bean getBean()
    {
        return new Bean(bean);
    }

    public void setBean(Bean bean)
    {
        this.bean = new Bean(bean);
    }
}