package com.jmp2017w.service.factory;

import com.jmp2017w.bean.Monster;
import com.jmp2017w.bean.monster.Cavalryman;
import com.jmp2017w.bean.monster.Dragon;
import com.jmp2017w.bean.monster.Hydra;
import com.jmp2017w.bean.monster.MonsterType;
import com.jmp2017w.bean.monster.Ogre;
import com.jmp2017w.bean.monster.Peasant;
import com.jmp2017w.bean.monster.ToxicSunflower;
import com.jmp2017w.service.MonsterAttributesConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates monsters using prototype approach.
 */
public final class MonsterPrototypeFactory
{
    private static Map<MonsterType, Monster> monsterZoo;
    private static Map<MonsterType, Monster> advancedMonsterZoo;

    static
    {
        monsterZoo = new HashMap<MonsterType, Monster>(MonsterType.values().length);

        Monster cavalryman = new Cavalryman(MonsterAttributesConstants.CAVALRYMAN_HEALTH,
                MonsterAttributesConstants.CAVALRYMAN_ATTACK, MonsterAttributesConstants.CAVALRYMAN_DEFENCE);
        monsterZoo.put(MonsterType.CAVALRYMAN, cavalryman);

        Monster dragon = new Dragon(MonsterAttributesConstants.DRAGON_HEALTH, MonsterAttributesConstants.DRAGON_ATTACK,
                MonsterAttributesConstants.DRAGON_DEFENCE);
        monsterZoo.put(MonsterType.DRAGON, dragon);

        Monster hydra = new Hydra(MonsterAttributesConstants.HYDRA_HEALTH, MonsterAttributesConstants.HYDRA_ATTACK,
                MonsterAttributesConstants.HYDRA_DEFENCE);
        monsterZoo.put(MonsterType.HYDRA, hydra);

        Monster ogre = new Ogre(MonsterAttributesConstants.OGRE_HEALTH, MonsterAttributesConstants.OGRE_ATTACK,
                MonsterAttributesConstants.OGRE_DEFENCE);
        monsterZoo.put(MonsterType.OGRE, ogre);

        Monster peasant = new Peasant(MonsterAttributesConstants.PEASANT_HEALTH, MonsterAttributesConstants.PEASANT_ATTACK,
                MonsterAttributesConstants.PEASANT_DEFENCE);
        monsterZoo.put(MonsterType.PEASANT, peasant);

        Monster toxicSunflower = new ToxicSunflower(MonsterAttributesConstants.TOXIC_SUNFLOWER_HEALTH,
                MonsterAttributesConstants.TOXIC_SUNFLOWER_ATTACK, MonsterAttributesConstants.TOXIC_SUNFLOWER_DEFENCE);
        monsterZoo.put(MonsterType.TOXIC_SUNFLOWER, toxicSunflower);
    }

    static
    {
        advancedMonsterZoo = new HashMap<MonsterType, Monster>(MonsterType.values().length);

        Monster advancedCavalryman = new Cavalryman(MonsterAttributesConstants.ADVANCED_CAVALRYMAN_HEALTH,
                MonsterAttributesConstants.ADVANCED_CAVALRYMAN_ATTACK, MonsterAttributesConstants.ADVANCED_CAVALRYMAN_DEFENCE);
        advancedMonsterZoo.put(MonsterType.CAVALRYMAN, advancedCavalryman);

        Monster advancedDragon = new Dragon(MonsterAttributesConstants.ADVANCED_DRAGON_HEALTH,
                MonsterAttributesConstants.ADVANCED_DRAGON_ATTACK, MonsterAttributesConstants.ADVANCED_DRAGON_DEFENCE);
        advancedMonsterZoo.put(MonsterType.DRAGON, advancedDragon);

        Monster advancedHydra = new Hydra(MonsterAttributesConstants.ADVANCED_HYDRA_HEALTH,
                MonsterAttributesConstants.ADVANCED_HYDRA_ATTACK, MonsterAttributesConstants.ADVANCED_HYDRA_DEFENCE);
        advancedMonsterZoo.put(MonsterType.HYDRA, advancedHydra);

        Monster advancedOgre = new Ogre(MonsterAttributesConstants.ADVANCED_OGRE_HEALTH,
                MonsterAttributesConstants.ADVANCED_OGRE_ATTACK, MonsterAttributesConstants.ADVANCED_OGRE_DEFENCE);
        advancedMonsterZoo.put(MonsterType.OGRE, advancedOgre);

        Monster advancedPeasant = new Peasant(MonsterAttributesConstants.ADVANCED_PEASANT_HEALTH,
                MonsterAttributesConstants.ADVANCED_PEASANT_ATTACK, MonsterAttributesConstants.ADVANCED_PEASANT_DEFENCE);
        advancedMonsterZoo.put(MonsterType.PEASANT, advancedPeasant);

        Monster advancedToxicSunflower = new ToxicSunflower(MonsterAttributesConstants.ADVANCED_TOXIC_SUNFLOWER_HEALTH,
                MonsterAttributesConstants.ADVANCED_TOXIC_SUNFLOWER_ATTACK,
                MonsterAttributesConstants.ADVANCED_TOXIC_SUNFLOWER_DEFENCE);
        advancedMonsterZoo.put(MonsterType.TOXIC_SUNFLOWER, advancedToxicSunflower);
    }

    /**
     * Creates and returns instance of <code>Monster</code> class.
     * @param type {@link MonsterType}
     * @param isAdvancedMonster indicates what monster we need to construct
     * @return {@link Monster} instance of <code>Monster</code> class
     */
    public static Monster createMonster(MonsterType type, boolean isAdvancedMonster)
    {
        Monster monster = isAdvancedMonster ? advancedMonsterZoo.get(type) : monsterZoo.get(type);
        if (monster != null)
        {
            monster = (Monster) monster.clone();
        }
        return monster;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private MonsterPrototypeFactory()
    {
        // nothing
    }
}