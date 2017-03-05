package com.jmp2017w.bean.monster;

/**
 * Defines monster types.
 */
public enum MonsterType
{
    CAVALRYMAN("Cavalryman"), DRAGON("Dragon"), HYDRA("Hydra"), OGRE("Ogre"), PEASANT("Peasant"), TOXIC_SUNFLOWER("ToxicSunflower");

    private String name;

    /**
     * Creates monster type.
     * @param name name of monster
     */
    MonsterType(String name)
    {
        this.name = name;
    }

    /**
     * Gets name of monster type.
     * @return {@link String} name of monster
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets random <code>MonsterType</code> object.
     * @return {@link MonsterType}
     */
    public static MonsterType random()
    {
        MonsterType[] allTypes = MonsterType.values();
        int randomIndex = (int) (Math.random() * allTypes.length);
        return allTypes[randomIndex];
    }
}