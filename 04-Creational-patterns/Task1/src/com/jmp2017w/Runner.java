package com.jmp2017w;

import com.jmp2017w.bean.Player;

/**
 * Factory Method Pattern
 * Abstract Factory Pattern
 * Singleton Pattern
 * Prototype Pattern
 * Builder Pattern
 * Object Pool Pattern
 *
 * Current application represents a game between two players.
 */
public class Runner
{
    /**
     * Entrance point to the application.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        Player uglyPapadopolus = new Player("Ugly Papadopolus");
        Player smartKaa = new Player("Smart Kaa");

        int roundNumber = 1;
        while (uglyPapadopolus.getState() == Player.State.ALIVE && smartKaa.getState() == Player.State.ALIVE)
        {
            System.out.println("Round #" + roundNumber);
            uglyPapadopolus.takeMonster();
            smartKaa.takeMonster();

            if (roundNumber % 2 == 1)
            {
                uglyPapadopolus.attack(smartKaa);
                smartKaa.attack(uglyPapadopolus);
            }
            else
            {
                smartKaa.attack(uglyPapadopolus);
                uglyPapadopolus.attack(smartKaa);
            }

            smartKaa.returnMonster();
            uglyPapadopolus.returnMonster();
            roundNumber++;
        }

        printVictoriousMessageIfPlayerIsAlive(uglyPapadopolus);
        printVictoriousMessageIfPlayerIsAlive(smartKaa);
    }

    /**
     * Prints victorious message if a player is alive.
     * @param player a player
     */
    private static void printVictoriousMessageIfPlayerIsAlive(Player player)
    {
        if (player != null && player.getState() == Player.State.ALIVE)
        {
            System.out.println("Player '" + player.getName() + "' won");
        }
    }
}