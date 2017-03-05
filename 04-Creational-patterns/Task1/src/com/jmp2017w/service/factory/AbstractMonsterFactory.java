package com.jmp2017w.service.factory;

import com.jmp2017w.bean.Monster;

/**
 * Creates {@link com.jmp2017w.bean.Monster} objects.
 */
public abstract class AbstractMonsterFactory
{
    /**
     * Creates {@link Monster} instance.
     * @return instance of <code>Monster</code> class
     */
    public abstract Monster create();
}