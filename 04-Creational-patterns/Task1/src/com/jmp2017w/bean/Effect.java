package com.jmp2017w.bean;

import com.jmp2017w.bean.effect.EffectType;

/**
 * Describes the effect that <code>Monster</code> can get. An effect can be either negative or positive.
 */
public abstract class Effect implements Prototype
{
    /**
     * Gets name of the bonus.
     * @return {@link String} bonus name
     */
    public abstract String getName();

    /**
     * Applies effect from the bonus.
     * @param monster a monster
     */
    public abstract void applyTo(Monster monster);

    /**
     * Gets type of bonus.
     * @return {@link EffectType} bonus type
     */
    public abstract EffectType getType();

    /**
     * {@inheritDoc
     */
    @Override
    public abstract Object clone();
}