package com.jmp2017w.bean.effect;

import com.jmp2017w.bean.Effect;
import com.jmp2017w.bean.Monster;

/**
 * This class is responsible for avoiding of attack.
 * If monster has this effect, it means that there is a chance to avoid enemy's attack.
 */
public class ChanceToAvoidAttackEffect extends Effect
{
    private double chanceToAvoidAttack;

    /**
     * Creates an instance of <code>ChanceToAvoidAttackEffect</code> class.
     * Possible values are from 0.0 to 1.0;
     * @param chanceToAvoidAttack chance to avoid attack
     * @throws IllegalArgumentException if parameter is out of range
     */
    public ChanceToAvoidAttackEffect(double chanceToAvoidAttack)
    {
        if (chanceToAvoidAttack < 0.0 || chanceToAvoidAttack > 1.0)
        {
            throw new IllegalArgumentException();
        }
        this.chanceToAvoidAttack = chanceToAvoidAttack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "Chance To Avoid Attack";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(Monster monster)
    {
        if (monster != null)
        {
            boolean shouldEffectBeApplied = (1.0 - Math.random()) <= chanceToAvoidAttack;
            if (shouldEffectBeApplied)
            {
                monster.setAttack(0);
            }
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
        return String.format("%s (%.2f)", getName(), chanceToAvoidAttack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone()
    {
        return new ChanceToAvoidAttackEffect(chanceToAvoidAttack);
    }
}