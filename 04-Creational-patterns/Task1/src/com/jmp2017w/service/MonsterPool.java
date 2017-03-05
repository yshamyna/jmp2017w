package com.jmp2017w.service;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.service.builder.AdvancedMonsterBuilder;
import com.jmp2017w.service.builder.MonsterBuilder;
import com.jmp2017w.service.factory.AdvancedMonsterFactory;
import com.jmp2017w.service.factory.MonsterFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Monster pool. Stores monsters that are in a game.
 */
public final class MonsterPool
{
    private static final int MAX_QUANTITY_OF_MONSTERS_IN_POOL = 10;
    /** Via builder ot factory. */
    private static final int COUNT_OF_CREATIONAL_APPROACHES = 2;
    private static final int BUILDER = 0;
    private static final int FACTORY = 1;
    /** Advanced monsters and simple monsters. */
    private static final int COUNT_OF_ABSTRACT_MONSTER_TYPES = 2;
    private static final int SIMPLE_MONSTER = 0;
    private static final int ADVANCED_MONSTER = 1;

    private Map<Long, Monster> pool;
    private MonsterProvider monsterProvider;
    private Set<Long> lockedMonsters;

    /**
     * Private constructor to prevent instantiation.
     */
    private MonsterPool()
    {
        pool = new HashMap<Long, Monster>();
        monsterProvider = new MonsterProvider();
        lockedMonsters = new HashSet<Long>();
    }

    /**
     * Gets monster from the pool.
     * @return {@link Monster} object
     */
    public Monster get()
    {
        Monster monster = null;
        while (monster == null)
        {
            long monsterIndex = (long) (Math.random() * MAX_QUANTITY_OF_MONSTERS_IN_POOL);
            monster = pool.get(monsterIndex);

            if (monster != null && lockedMonsters.contains(monster.getId()))
            {
                monster = null;
                continue;
            }

            if ((monster != null && monster.getState() == Monster.State.DEAD) || monster == null)
            {
                monster = createMonster();
                pool.put(monsterIndex, monster);
            }
        }
        return monster;
    }

    /**
     * Returns monster to the pool.
     * @param id ID of a monster
     */
    public void returnMonster(long id)
    {
        lockedMonsters.remove(id);
    }

    /**
     * Creates a monster.
     * @return {@link Monster} object
     */
    private Monster createMonster()
    {
        Monster newMonster;
        int builderOrFactory = (int) (Math.random() * COUNT_OF_CREATIONAL_APPROACHES);
        switch (builderOrFactory)
        {
            case BUILDER:
                newMonster = createMonsterViaBuilder();
                break;
            case FACTORY:
            default:
                newMonster = createMonsterViaFactory();

        }
        return newMonster;
    }

    /**
     * Creates monster via builder.
     * @return {@link Monster} object
     */
    private Monster createMonsterViaBuilder()
    {
        Monster newMonster;
        int simpleOrAdvancedMonster = (int) (Math.random() * COUNT_OF_ABSTRACT_MONSTER_TYPES);
        switch (simpleOrAdvancedMonster)
        {
            case ADVANCED_MONSTER:
                newMonster = monsterProvider.buildMonster(new AdvancedMonsterBuilder());
                break;
            case SIMPLE_MONSTER:
            default:
                newMonster = monsterProvider.buildMonster(new MonsterBuilder());
                break;
        }
        return newMonster;
    }

    /**
     * Creates monster via factory.
     * @return {@link Monster} object
     */
    private Monster createMonsterViaFactory()
    {
        Monster newMonster;
        int simpleOrAdvancedMonster = (int) (Math.random() * COUNT_OF_ABSTRACT_MONSTER_TYPES);
        switch (simpleOrAdvancedMonster)
        {
            case ADVANCED_MONSTER:
                newMonster = monsterProvider.createMonster(new AdvancedMonsterFactory());
                break;
            case SIMPLE_MONSTER:
            default:
                newMonster = monsterProvider.createMonster(new MonsterFactory());
                break;
        }
        return newMonster;
    }

    /**
     * Gets an instance of <code>MonsterPool</code> class.
     * @return instance of <code>MonsterPool</code> class
     */
    public static MonsterPool getInstance()
    {
        return MonsterPoolHolder.MONSTER_POOL_INSTANCE;
    }


    /**
     * Class that keeps reference to {@link MonsterPool} instance.
     */
    private static class MonsterPoolHolder
    {
        private static final MonsterPool MONSTER_POOL_INSTANCE = new MonsterPool();
    }
}