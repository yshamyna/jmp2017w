package com.jmp2017w.service.builder;

import com.jmp2017w.bean.effect.ApplyWeaknessEffect;
import com.jmp2017w.bean.effect.ChanceToAvoidAttackEffect;
import com.jmp2017w.bean.effect.CriticalDamageEffect;
import com.jmp2017w.bean.monster.MonsterType;
import com.jmp2017w.service.factory.MonsterPrototypeFactory;

/**
 * Creates advanced monsters.
 */
public class AdvancedMonsterBuilder extends AbstractMonsterBuilder
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareMonster()
    {
        monster = MonsterPrototypeFactory.createMonster(MonsterType.random(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildApplyWeaknessEffect()
    {
        if (monster != null)
        {
            monster.addEffect(new ApplyWeaknessEffect());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildChanceToAvoidAttackEffect()
    {
        if (monster != null)
        {
            double chance = Math.random();
            monster.addEffect(new ChanceToAvoidAttackEffect(chance));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildCriticalDamageEffect()
    {
        if (monster != null)
        {
            double chance = Math.random();
            monster.addEffect(new CriticalDamageEffect(chance));
        }
    }
}