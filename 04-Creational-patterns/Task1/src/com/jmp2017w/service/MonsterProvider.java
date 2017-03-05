package com.jmp2017w.service;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.service.builder.AbstractMonsterBuilder;
import com.jmp2017w.service.factory.AbstractMonsterFactory;

/**
 * Class is responsible for providing new monster.
 */
public class MonsterProvider
{
    /**
     * Builds a monster.
     * @param monsterBuilder monster builder
     * @return {@link Monster} object
     */
    public Monster buildMonster(AbstractMonsterBuilder monsterBuilder)
    {
        Monster monster = null;
        if (monsterBuilder != null)
        {
            monsterBuilder.prepareMonster();
            monsterBuilder.buildApplyWeaknessEffect();
            monsterBuilder.buildChanceToAvoidAttackEffect();
            monsterBuilder.buildCriticalDamageEffect();
            monster = monsterBuilder.getMonster();
        }

        return monster;
    }

    /**
     * Creates a monster.
     * @param monsterFactory monster factory
     * @return {@link Monster} object
     */
    public Monster createMonster(AbstractMonsterFactory monsterFactory)
    {
        Monster monster = null;
        if (monsterFactory != null)
        {
            monster = monsterFactory.create();
        }
        return monster;
    }
}