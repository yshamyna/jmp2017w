package com.jmp2017w.bean.effect;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * This class is responsible for adding critical damage effect.
 * If monster has this effect, it means that there is a chance to hit x2 damage (attack is multiplied by 2).
 */
public class CriticalDamageEffect extends Effect
{
    private double chanceToThrowCriticalDamage;

    /**
     * Creates an instance of <code>CriticalDamageEffect</code> class.
     * Possible values are from 0.0 to 1.0;
     * @param chanceToThrowCriticalDamage chance to throw critical damage
     * @throws IllegalArgumentException if parameter is out of range
     */
    public CriticalDamageEffect(double chanceToThrowCriticalDamage)
    {
        this.chanceToThrowCriticalDamage = chanceToThrowCriticalDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "Critical Damage";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(Monster monster)
    {
        if (monster != null)
        {
            boolean shouldEffectBeApplied = (1.0 - Math.random()) <= chanceToThrowCriticalDamage;
            if (shouldEffectBeApplied)
            {
                int currentAttack = monster.getAttack();
                int newAttack = currentAttack << 1;
                monster.setAttack(newAttack);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EffectType getType()
    {
        return EffectType.AGAINST_CURRENT_MONSTER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("%s (%.2f)", getName(), chanceToThrowCriticalDamage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        return new CriticalDamageEffect(chanceToThrowCriticalDamage);
    }
}