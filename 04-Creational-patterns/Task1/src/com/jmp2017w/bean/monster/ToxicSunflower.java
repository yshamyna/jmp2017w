package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * Describes a toxic sunflower.
 */
public class ToxicSunflower extends Monster
{
    /**
     * {@inheritDoc}
     */
    public ToxicSunflower(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MonsterType getType()
    {
        return MonsterType.TOXIC_SUNFLOWER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        ToxicSunflower toxicSunflower = new ToxicSunflower(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            toxicSunflower.addEffect(effect);
        }
        return toxicSunflower;
    }
}