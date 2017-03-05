package com.jmp2017w.bean;

import com.jmp2017w.bean.monster.MonsterType;

import java.util.LinkedList;
import java.util.List;

/**
 * Base class for all creatures.
 */
public abstract class Monster implements Prototype
{
    private static long quantityOfInstances = 0L;

    private long id;
    private State state;
    private int health;
    private int attack;
    private int defence;
    private List<Effect> effects;

    public Monster(int health, int attack, int defence)
    {
        id = ++quantityOfInstances;
        state = State.ALIVE;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.effects = new LinkedList<Effect>();
    }

    /**
     * Returns ID of the monster.
     * @return ID
     */
    public long getId()
    {
        return id;
    }

    /**
     * Gets state of current monster.
     * @return <code>ALIVE</code> if quantity of HP is greater than zero, otherwise - <code>DIED</code>
     */
    public State getState()
    {
        return state;
    }

    /**
     * Gets health points of the monster.
     * @return health points
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets new health value.
     * @param health new health value
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Gets attack points of the monster.
     * @return attack points
     */
    public int getAttack()
    {
        return attack;
    }

    /**
     * Sets new attack value.
     * @param attack new attack value
     */
    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    /**
     * Gets defence points of the monster.
     * @return defence points
     */
    public int getDefence()
    {
        return defence;
    }

    /**
     * Sets new defence value.
     * @param defence new defence value
     */
    public void setDefence(int defence)
    {
        this.defence = defence;
    }

    /**
     * Gets type of the monster.
     * @return {@link MonsterType} type of the monster
     */
    public abstract MonsterType getType();

    /**
     * Adds an effect to current monster. Does not add NULL-values.
     * @param effect some effect.
     */
    public void addEffect(Effect effect)
    {
        if (effect != null)
        {
            effects.add(effect);
        }
    }

    /**
     * Gets clone list of effects.
     * @return clone of effects
     */
    public List<Effect> getEffects()
    {
        List<Effect> effectsClone = new LinkedList<Effect>();
        for (Effect effect : effects)
        {
            effectsClone.add((Effect) effect.clone());
        }
        return effectsClone;
    }

    /**
     * Attacks other monster.
     * @param monster monster to attack
     */
    public void attack(Monster monster)
    {
        if (monster != null)
        {
            Monster attackerMonster = (Monster) clone();
            Monster defenderMonster = (Monster) monster.clone();

            applyEffectsTo(attackerMonster.effects, attackerMonster, defenderMonster);
            applyEffectsTo(defenderMonster.effects, defenderMonster, attackerMonster);

            defenderMonster.applyDamage(attackerMonster.getAttack());
            monster.setHealth(defenderMonster.getHealth());

            if (monster.getHealth() <= 0)
            {
                monster.state = State.DEAD;
                System.out.println(String.format("Monster [%s] is dead", monster.getType().getName()));
            }
        }
    }

    /**
     * Applies effects to monsters.
     * @param effects effects to apply
     * @param currentMonster current monster
     * @param enemyMonster enemy monster
     */
    private void applyEffectsTo(List<Effect> effects, Monster currentMonster, Monster enemyMonster)
    {
        for (Effect effect : effects)
        {
            switch (effect.getType())
            {
                case AGAINST_CURRENT_MONSTER:
                    effect.applyTo(currentMonster);
                    break;
                case AGAINST_ENEMIES:
                    effect.applyTo(enemyMonster);
                    break;
                default:
                    // nothing
            }
        }
    }

    /**
     * Applies damage.
     * @param damage damage to apply
     */
    private void applyDamage(int damage)
    {
        if (damage > 0)
        {
            int damageToBeApplied = damage - defence;
            if (damageToBeApplied <= 0)
            {
                damageToBeApplied = damage / 2;
            }
            health -= damageToBeApplied;
            String message = String.format("Defender monster [%s] has got %d damage. Health has been decreased to %d",
                    getType().getName(), damageToBeApplied, health);
            System.out.println(message);
        }
        else
        {
            System.out.println(String.format("Monster [%s] avoids attack", getType().getName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder(getType().getName());
        stringBuilder.append(String.format("[health = %d, attack = %d, defence = %d]",health, attack, defence));
        if (effects == null || effects.isEmpty())
        {
            stringBuilder.append(" with no effects");
        }
        else
        {
            stringBuilder.append(" with effects [");
            for (Effect effect : effects)
            {
                stringBuilder.append(effect);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    /**
     * {@inheritDoc
     */
    @Override
    public abstract Object clone();


    /**
     * Defines possible states of the monster.
     */
    public enum State
    {
        ALIVE, DEAD
    }
}