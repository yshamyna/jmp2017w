package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * Describes an ogre.
 */
public class Ogre extends Monster
{
    /**
     * {@inheritDoc}
     */
    public Ogre(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
        Ogre ogre = new Ogre(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            ogre.addEffect(effect);
        }
        return ogre;
    }
}