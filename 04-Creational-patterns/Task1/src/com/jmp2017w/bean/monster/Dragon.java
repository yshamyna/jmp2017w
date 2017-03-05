package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * Describes a dragon.
 */
public class Dragon extends Monster
{
    /**
     * {@inheritDoc}
     */
    public Dragon(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MonsterType getType()
    {
        return MonsterType.DRAGON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        Dragon dragon = new Dragon(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            dragon.addEffect(effect);
        }
        return dragon;
    }
}