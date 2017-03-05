package com.jmp2017w.service.builder;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.bean.effect.ApplyWeaknessEffect;

/**
 * Provides methods to build {@link com.jmp2017w.bean.Monster} objects.
 */
public abstract class AbstractMonsterBuilder
{
    protected Monster monster;

    /**
     * Gets instance of <code>Monster</code> class.
     * @return {@link Monster} object
     */
    public Monster getMonster()
    {
        return monster;
    }

    /**
     * Creates and initializes {@link Monster} object.
     */
    public abstract void prepareMonster();

    /**
     * Adds {@link ApplyWeaknessEffect} to new {@link Monster}
     */
    public abstract void buildApplyWeaknessEffect();

    /**
     * Adds {@link com.jmp2017w.bean.effect.ChanceToAvoidAttackEffect} to new {@link Monster}
     */
    public abstract void buildChanceToAvoidAttackEffect();

    /**
     * Adds {@link com.jmp2017w.bean.effect.CriticalDamageEffect} to new {@link Monster}
     */
    public abstract void buildCriticalDamageEffect();
}