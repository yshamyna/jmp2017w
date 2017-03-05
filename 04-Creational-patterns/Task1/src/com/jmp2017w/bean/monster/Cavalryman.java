package com.jmp2017w.bean.monster;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;


/**
 * Describes a cavalryman.
 */
public class Cavalryman extends Monster
{
    /**
     * {@inheritDoc}
     */
    public Cavalryman(int health, int attack, int defence)
    {
        super(health, attack, defence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MonsterType getType()
    {
        return MonsterType.CAVALRYMAN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        Cavalryman cavalryman = new Cavalryman(getHealth(), getAttack(), getDefence());
        for (Effect effect : getEffects())
        {
            cavalryman.addEffect(effect);
        }
        return cavalryman;
    }
}