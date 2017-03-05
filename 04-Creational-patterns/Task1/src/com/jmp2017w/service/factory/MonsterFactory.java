package com.jmp2017w.service.factory;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.bean.monster.MonsterType;

/**
 * Creates monsters.
 */
public class MonsterFactory extends AbstractMonsterFactory
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Monster create()
    {
        return MonsterPrototypeFactory.createMonster(MonsterType.random(), false);
    }
}
