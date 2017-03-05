package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * Describes a peasant.
 */
public class Peasant extends Monster
{
    /**
     * {@inheritDoc}
     */
    public Peasant(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * /**
     * {@inheritDoc}
     */
    @Override
    public MonsterType getType()
    {
        return MonsterType.PEASANT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        Peasant peasant = new Peasant(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            peasant.addEffect(effect);
        }
        return peasant;
    }
}