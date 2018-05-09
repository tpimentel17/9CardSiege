package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import static logic.Constants.*;
import logic.cards.*;

public class GameData implements Serializable {

    private int currentDay;
    private PlayerStats playerStats;
    private EnemyTracks enemyTracks;
    private Die die;
    private ArrayList<Card> deck;
    private ArrayList<Card> drawnCards;

    private boolean gameFinish;
    private boolean gameWon;

    public GameData() {
        playerStats = new PlayerStats();
        enemyTracks = new EnemyTracks();
        die = new Die();
        deck = new ArrayList<>(7);
        drawnCards = new ArrayList<>(7);
    }

    public boolean initialize() {
        gameFinish = false;
        gameWon = false;
        currentDay = 1;
        deck.add(new Card1());
        deck.add(new Card2());
        deck.add(new Card3());
        deck.add(new Card4());
        deck.add(new Card5());
        deck.add(new Card6());
        deck.add(new Card7());
        Collections.shuffle(deck);

        return true;
    }

    //GETTERS
    public int getCurrentDay() {
        return currentDay;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public EnemyTracks getEnemyTracks() {
        return enemyTracks;
    }

    public Die getDie() {
        return die;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDrawnCards() {
        return drawnCards;
    }

    public boolean isGameFinish() {
        return gameFinish;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    //STATE TRANSITION ACTIONS
    public boolean drawTopCard() {

        //Remove carta do baralho e adiciona ao baralho de removidas        
        Card card = deck.get(0);
        deck.remove(0);
        drawnCards.add(card);

        if (card.getDay(currentDay).isTrebuchetEvent()) {

            switch (enemyTracks.getNumberOfTrebuchets()) {
                case 1:
                    die.roll();
                    if (die.getValue() > 3) {
                        playerStats.damageWall(1);
                    }
                    break;
                case 2:
                    playerStats.damageWall(1);
                    break;
                case 3:
                    playerStats.damageWall(2);
                    break;
            }
        } else {
            ArrayList<String> enemyOrders = card.getDay(currentDay).getEnemyOrders();

            for (String s : enemyOrders) {
                switch (s) {
                    case LADDERS:
                        enemyTracks.moveForward(LADDERS);
                        if (enemyTracks.getLaddersPosition() == 0) {
                            playerStats.reduceMorale();
                        }
                        break;
                    case BATTERING_RAM:
                        enemyTracks.moveForward(BATTERING_RAM);
                        if (enemyTracks.getRamPosition() == 0) {
                            playerStats.reduceMorale();
                        }
                        break;
                    case SIEGE_TOWER:
                        enemyTracks.moveForward(SIEGE_TOWER);
                        if (enemyTracks.getTowerPosition() == 0) {
                            playerStats.reduceMorale();
                        }

                        break;
                    case SLOWEST_UNIT:
                        enemyTracks.moveForward(SLOWEST_UNIT);
                        if (enemyTracks.getLaddersPosition() == 0) {
                            playerStats.reduceMorale();
                        }
                        if (enemyTracks.getRamPosition() == 0) {
                            playerStats.reduceMorale();
                        }
                        if (enemyTracks.getTowerPosition() == 0) {
                            playerStats.reduceMorale();
                        }
                        break;
                }
            }
        }

        if (enemyTracks.getNumberOfUnitsInCloseCombat() > 2 || playerStats.getNumberOfZeroStats() > 1) {
            gameFinish = true;
        }
        
        return gameFinish;
    }

}
