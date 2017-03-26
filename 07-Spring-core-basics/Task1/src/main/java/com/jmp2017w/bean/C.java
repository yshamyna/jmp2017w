package com.jmp2017w.bean;

/**
 * Bean C.
 */
public abstract class C
{
    /**
     * Lookup Method Injector
     * @return {@link D}
     */
    protected abstract D buildDBean();

    @Override
    public String toString()
    {
        return String.format("C{d=%s}", buildDBean());
    }
}