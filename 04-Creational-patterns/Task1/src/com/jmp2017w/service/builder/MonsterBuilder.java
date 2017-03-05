package com.jmp2017w.service.builder;

import com.jmp2017w.bean.effect.ChanceToAvoidAttackEffect;
import com.jmp2017w.bean.effect.CriticalDamageEffect;
import com.jmp2017w.bean.monster.MonsterType;
import com.jmp2017w.service.factory.MonsterPrototypeFactory;

/**
 * Builds monsters.
 */
public class MonsterBuilder extends AbstractMonsterBuilder
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareMonster()
    {
        monster = MonsterPrototypeFactory.createMonster(MonsterType.random(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildApplyWeaknessEffect()
    {
        // nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildChanceToAvoidAttackEffect()
    {
        if (monster != null)
        {
            monster.addEffect(new ChanceToAvoidAttackEffect(0.2));
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
            monster.addEffect(new CriticalDamageEffect(0.2));
        }
    }
}