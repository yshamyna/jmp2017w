package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * Describes a hydra.
 */
public class Hydra extends Monster
{
    /**
     * {@inheritDoc}
     */
    public Hydra(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * {@inheritDoc}
     */
    public MonsterType getType()
    {
        return MonsterType.HYDRA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        Hydra hydra = new Hydra(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            hydra.addEffect(effect);
        }
        return hydra;
    }
}