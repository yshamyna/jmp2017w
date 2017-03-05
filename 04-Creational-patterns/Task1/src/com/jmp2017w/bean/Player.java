package com.jmp2017w.bean;

import com.jmp2017w.service.MonsterPool;

/**
 * Represents a player.
 */
public class Player
{
    private String name;
    private int health;
    private Monster monster;
    private State state;

    /**
     * Creates {@link Player} instance.
     * @param name name of new player.
     */
    public Player(String name)
    {
        this.name = name;
        this.health = 100;
        state = State.ALIVE;
    }

    /**
     * Gets state of the player.
     * @return <code>ALIVE</code> if quantity of HP is greater than zero, otherwise - <code>DIED</code>
     */
    public State getState()
    {
        return state;
    }

    /**
     * Gets name of the player.
     * @return {@link String} name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Takes a monster from the pool of monsters.
     */
    public void takeMonster()
    {
        MonsterPool monsterPool = MonsterPool.getInstance();
        monster = monsterPool.get();
        System.out.println(String.format("Player '%s' [health = %d] took %s", name, health, monster));
    }

    /**
     * Returns monster to the pool.
     */
    public void returnMonster()
    {
        MonsterPool monsterPool = MonsterPool.getInstance();
        monsterPool.returnMonster(monster.getId());
        monster = null;
    }

    /**
     * Attacks other player.
     * @param enemy other player
     */
    public void attack(Player enemy)
    {
        if (getState() == State.ALIVE)
        {
            if (monster == null || monster.getState() == Monster.State.DEAD)
            {
                System.out.println("Player '" + name + "' skips this round as his monster is dead.");
            }
            else
            {
                System.out.println(String.format("'%s' attacks '%s'", name, enemy.name));
                monster.attack(enemy.monster);
                enemy.updateHealth();
            }
        }
    }

    /**
     * Updates HP of player in concordance with HP of the monster.
     */
    private void updateHealth()
    {
        if (monster != null && monster.getState() == Monster.State.DEAD)
        {
            health += monster.getHealth();
            System.out.println("Health of '" + name + "' is decreased to " + health);
        }
        if (health <= 0)
        {
            state = State.DEAD;
            System.out.println("Player '" + name + "' is DEAD.");
        }
    }

    /**
     * Defines possible states of the player.
     */
    public enum State
    {
        ALIVE, DEAD
    }
}