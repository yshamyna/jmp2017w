package com.jmp2017w.bean.effect;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * This class is responsible for adding weakness to enemies.
 * If monster has this effect, it means that enemy monster gets defence divided by 2.
 */
public class ApplyWeaknessEffect extends Effect
{
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "Apply Weakness Against Enemies";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(Monster monster)
    {
        if (monster != null)
        {
            int currentDefence = monster.getDefence();
            int newDefence = currentDefence >> 1;
            if (newDefence == 0)
            {
                newDefence++;
            }
            monster.setDefence(newDefence);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getType()
    {
        return EffectType.AGAINST_ENEMIES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("%s", getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        return new ApplyWeaknessEffect();
    }
}