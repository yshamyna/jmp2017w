package com.jmp2017w.service.factory;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.bean.monster.MonsterType;

/**
 * Creates advanced monsters.
 */
public class AdvancedMonsterFactory extends AbstractMonsterFactory
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Monster create()
    {
        return MonsterPrototypeFactory.createMonster(MonsterType.random(), true);
    }
}