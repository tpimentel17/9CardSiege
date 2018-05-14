package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import static logic.Constants.*;
import logic.cards.*;

public class GameData implements Serializable {

    private int gameStatus;
    private int currentDay;
    private int currentActionPoints;
    private PlayerStats playerStats;
    private EnemyTracks enemyTracks;
    private Die die;
    private ArrayList<Card> deck;
    private ArrayList<Card> drawnCards;

    private boolean gameFinish;
    private boolean boiledWaterWasUsed;

    public GameData() {
        playerStats = new PlayerStats();
        enemyTracks = new EnemyTracks();
        die = new Die();
        deck = new ArrayList<>(7);
        drawnCards = new ArrayList<>(7);
    }

    public boolean initialize() {
        gameStatus = CONTINUE;
        boiledWaterWasUsed = false;
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

    // <editor-fold desc="GETTERS">
    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentActionPoints() {
        return currentActionPoints;
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

    // </editor-fold>
    // <editor-fold desc="SETTERS">
    public void setDefaultStatus() {
        gameStatus = CONTINUE;
    }

    // </editor-fold>
    // <editor-fold desc="PUBLIC METHODS">
    public int checkGameStatus() {
        endOfTurnCheck();
        endOfDayCheck();

        return gameStatus;
    }

    //</editor-fold>
    // <editor-fold desc="PRIVATE METHODS">
    private boolean endOfTurnLossCheck() {
        if (enemyTracks.getNumberOfUnitsInCloseCombat() >= 2 || playerStats.getNumberOfZeroStats() > 0) {
            return true;
        }
        return false;
    }

    private void reshuffleDeck() {
        deck.addAll(drawnCards);
        drawnCards.clear();
        Collections.shuffle(deck);

    }

    private boolean endOfDayCheck() {
        if (deck.isEmpty()) {
            playerStats.reduceSupplies(1);
            currentDay++;
            reshuffleDeck();

            if (playerStats.getSoldiersLocation() != ENEMY_LINES) {
                playerStats.addSupplies(playerStats.getNumberOfRaidedSupplies());
                playerStats.clearRaidedSupplies();
                playerStats.moveSoldiers(CASTLE);
            } else {
                playerStats.moveSoldiers(CASTLE);
                playerStats.clearRaidedSupplies();
                playerStats.reduceMorale();
            }

            if (playerStats.getSupplies() == 0 || playerStats.getMorale() == 0) {
                gameStatus = DEFEAT;
            } else if (currentDay == 3) {
                gameStatus = VICTORY;
            }
        }
        return true;
    }

    private boolean endOfTurnCheck() {
        if (currentActionPoints == 0) {
            gameStatus = DRAW_CARD;
            boiledWaterWasUsed = false;
            if (endOfTurnLossCheck()) {
                gameStatus = DEFEAT;
            }
            return true;
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold desc="STATE TRANSITION ACTIONS">
    public boolean drawTopCard() {

        //Remove carta do baralho e adiciona ao baralho de removidas        
        Card card = deck.get(0);
        deck.remove(0);
        drawnCards.add(card);

        currentActionPoints = card.getDay(currentDay).getActionPoints();

        System.out.println("\n" + card.toString() + " was drawn.");

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

    public boolean archersAttack() {
        if (currentActionPoints == 0) {

            System.out.println("\n\n***************************************************************");
            System.out.println("[INVALID ACTION] You don't have enough Action Points Available!");
            System.out.println("***************************************************************");

            return false;
        }
        return true;
    }

    public boolean boilingWaterAttack() {
        if (currentActionPoints == 0) {
            System.out.println("\n\n***************************************************************");
            System.out.println("[INVALID ACTION] You don't have enough Action Points Available!");
            System.out.println("***************************************************************");
            return false;
        } else if (boiledWaterWasUsed == true) {
            System.out.println("\n\n*********************************************************************");
            System.out.println("[INVALID ACTION] Boiling Water Atack was already used once this turn!");
            System.out.println("*********************************************************************");
            return false;
        } else if (enemyTracks.getNumberOfUnitsInCircleSpaces() == 0) {
            System.out.println("\n\n***********************************************************");
            System.out.println("[INVALID ACTION] There are no enemies in the circle spaces!");
            System.out.println("***********************************************************");
            return false;
        }
        return true;
    }

    public boolean attackSelectedTrack(String action, String selectedTrack) {

        //Archers Attack
        if (action.equals(ARCHERS)) {
            die.roll();

            switch (selectedTrack) {
                case LADDERS:
                    currentActionPoints--;
                    if (enemyTracks.getLaddersPosition() == 0) {
                        if (die.getValue() > 4) {
                            enemyTracks.moveBackwards(LADDERS);
                        } else {
                            System.out.println("The attack wasn't effective!");
                        }
                    } else {
                        if (die.getValue() > 2) {
                            enemyTracks.moveBackwards(LADDERS);
                        } else {
                            System.out.println("The attack wasn't effective!");
                        }
                    }
                    break;
                case BATTERING_RAM:
                    currentActionPoints--;
                    if (enemyTracks.getRamPosition() == 0) {
                        if (die.getValue() > 4) {
                            enemyTracks.moveBackwards(BATTERING_RAM);
                        } else {
                            System.out.println("The attack wasn't effective!");
                        }
                    } else {
                        if (die.getValue() > 3) {
                            enemyTracks.moveBackwards(BATTERING_RAM);
                        } else {
                            System.out.println("The attack wasn't effective!");
                        }
                    }
                    break;
                case SIEGE_TOWER:
                    currentActionPoints--;
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(SIEGE_TOWER);
                    } else {
                        System.out.println("The attack wasn't effective!");
                    }
                    break;
            }

            //Boiling Water Attack
        } else if (action.equals(BOILING_WATER)) {
            switch (selectedTrack) {
                case LADDERS:
                    if (enemyTracks.getRamPosition() != 1) {
                        System.out.println("\n\n**********************************************************");
                        System.out.println("[INVALID ACTION] There are no Ladders in the circle space!");
                        System.out.println("**********************************************************");
                        return false;
                    }

                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() + 1 > 2) {
                        enemyTracks.moveBackwards(LADDERS);
                    } else {
                        System.out.println("The Boiling Water Attack wasn't effective!");
                    }
                    break;
                case BATTERING_RAM:
                    if (enemyTracks.getRamPosition() != 1) {
                        System.out.println("\n\n*****************************************************************");
                        System.out.println("[INVALID ACTION] There isn't a Battering Ram in the circle space!");
                        System.out.println("*****************************************************************");
                        return false;
                    }

                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() + 1 > 3) {
                        enemyTracks.moveBackwards(BATTERING_RAM);
                    } else {
                        System.out.println("The Boiling Water Attack wasn't effective!");
                    }
                    break;
                case SIEGE_TOWER:
                    if (enemyTracks.getTowerPosition() != 1) {
                        System.out.println("\n\n***************************************************************");
                        System.out.println("[INVALID ACTION] There isn't a Siege Tower in the circle space!");
                        System.out.println("***************************************************************");
                        return false;
                    }

                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() + 1 > 4) {
                        enemyTracks.moveBackwards(SIEGE_TOWER);
                    } else {
                        System.out.println("The Boiling Water Attack wasn't effective!");
                    }
                    break;
            }
            boiledWaterWasUsed = true;
        } else {
            switch (selectedTrack) {
                case LADDERS:
                    if (enemyTracks.getRamPosition() != 0) {
                        System.out.println("\n\n****************************************************************");
                        System.out.println("[INVALID ACTION] There are no Ladders in the close combat area!");
                        System.out.println("****************************************************************");
                        return false;
                    }

                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(LADDERS);
                    } else {
                        System.out.println("The attack wasn't effective!");
                    }
                    break;

                case BATTERING_RAM:
                    if (enemyTracks.getRamPosition() != 0) {
                        System.out.println("\n\n**********************************************************************");
                        System.out.println("[INVALID ACTION] There isn't a Battering Ram in the close combat area!");
                        System.out.println("**********************************************************************");
                        return false;
                    }

                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(BATTERING_RAM);
                    } else {
                        System.out.println("The attack wasn't effective!");
                    }
                    break;

                case SIEGE_TOWER:
                    if (enemyTracks.getTowerPosition() != 0) {
                        System.out.println("\n\n********************************************************************");
                        System.out.println("[INVALID ACTION] There isn't a Siege Tower in the close combat area!");
                        System.out.println("********************************************************************");
                        return false;
                    }
                    currentActionPoints--;
                    die.roll();
                    if (die.getValue() == 1) {
                        playerStats.reduceMorale();;
                    }
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(SIEGE_TOWER);
                    } else {
                        System.out.println("The attack wasn't effective!");
                    }
                    break;
            }
        }

        return true;
    }

    public boolean closeCombat() {
        if (currentActionPoints == 0) {
            System.out.println("\n\n***************************************************************");
            System.out.println("[INVALID ACTION] You don't have enough Action Points Available!");
            System.out.println("***************************************************************");
            return false;
        } else if (enemyTracks.getNumberOfUnitsInCloseCombat() == 0) {
            System.out.println("\n\n**************************************************************");
            System.out.println("[INVALID ACTION] There are no enemies in the close combat area");
            System.out.println("**************************************************************");
            return false;
        }
        return true;
    }
    // </editor-fold>

    public void coupure() {
        currentActionPoints--;
        die.roll();
        if (die.getValue() > 4) {
            playerStats.repairWall();
        }
    }

    public boolean rallyTroopsOptions() {
        if (currentActionPoints == 0) {
            System.out.println("\n\n***************************************************************");
            System.out.println("[INVALID ACTION] You don't have enough Action Points Available!");
            System.out.println("***************************************************************");
            return false;
        }
        return true;
    }

    public void rallyTroops(boolean drm) {
        currentActionPoints--;
        die.roll();
        if (drm) {
            playerStats.reduceSupplies(1);
            if (die.getValue() + 1 > 4) {
                playerStats.increaseMorale();
            }
        } else {
            if (die.getValue() > 4) {
                playerStats.increaseMorale();
            }
        }

    }
}
