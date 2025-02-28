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
    private PlayerTracks playerTracks;
    private EnemyTracks enemyTracks;
    private Die die;
    private ArrayList<Card> deck;
    private ArrayList<Card> drawnCards;

    private boolean gameFinish;
    private boolean boiledWaterWasUsed;
    private boolean freeMovementWasUsed;
    private boolean additionalActionalActionPointWasUsed;

    private ArrayList<String> messageLog;

    public GameData() {
        initialize();
    }

    public final boolean initialize() {
        messageLog = new ArrayList<>();
        die = new Die(this);
        drawnCards = new ArrayList<>(7);
        playerTracks = new PlayerTracks(this);
        enemyTracks = new EnemyTracks(this);
        deck = new ArrayList<>(7);

        deck.add(new Card1());
        deck.add(new Card2());
        deck.add(new Card3());
        deck.add(new Card4());
        deck.add(new Card5());
        deck.add(new Card6());
        deck.add(new Card7());

        gameStatus = CONTINUE;
        boiledWaterWasUsed = false;
        freeMovementWasUsed = false;
        additionalActionalActionPointWasUsed = false;
        currentDay = 1;

        Collections.shuffle(deck);

        currentActionPoints = 0;
        drawnCards.clear();
        gameFinish = false;

        return true;
    }

    // <editor-fold desc="GETTERS">
    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentActionPoints() {
        return currentActionPoints;
    }

    public PlayerTracks getPlayerTracks() {
        return playerTracks;
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

    public boolean boiledWaterWasUsed() {
        return boiledWaterWasUsed;
    }

    public int getNumberOfEnemiesInCircleSpaces() {
        return enemyTracks.getNumberOfUnitsInCircleSpaces();
    }

    public int getNumberOfEnemiesInCloseCombat() {
        return enemyTracks.getNumberOfUnitsInCloseCombat();
    }

    public boolean freeMovementWasUsed() {
        return freeMovementWasUsed;
    }

    public boolean additionalActionalActionPointWasUsed() {
        return additionalActionalActionPointWasUsed;
    }

    public int getSoldiersLocation() {
        return playerTracks.getSoldiersLocation();
    }

    public int getMoralePoints() {
        return playerTracks.getMorale();
    }

    public int getSuppliesPoints() {
        return playerTracks.getSupplies();
    }

    // </editor-fold>
    // <editor-fold desc="PUBLIC METHODS">
    public void clearMessageLog() {
        messageLog.clear();
    }

    public void addMessageLog(String msg) {
        messageLog.add(msg);
    }

    public ArrayList<String> getMessageLog() {
        return messageLog;
    }

    public void setDefaultStatus() {
        gameStatus = CONTINUE;
    }

    public int checkGameStatus() {
        endOfTurnCheck();
        endOfDayCheck();

        return gameStatus;
    }
    //</editor-fold>

    // <editor-fold desc="PRIVATE METHODS">
    private boolean endOfTurnLossCheck() {
        return enemyTracks.getNumberOfUnitsInCloseCombat() >= 2 || playerTracks.getNumberOfZeroStats() > 0;
    }

    private void reshuffleDeck() {
        deck.addAll(drawnCards);
        drawnCards.clear();
        Collections.shuffle(deck);

    }

    private boolean endOfDayCheck() {
        if (deck.isEmpty()) {
            playerTracks.reduceSupplies(1);
            currentDay++;
            reshuffleDeck();

            if (playerTracks.getSoldiersLocation() != ENEMY_LINES) {
                playerTracks.addSupplies(playerTracks.getNumberOfRaidedSupplies());
                playerTracks.clearRaidedSupplies();
                playerTracks.moveSoldiers(CASTLE);
            } else {
                captureSoldiersProcedure();
            }

            if (playerTracks.getSupplies() == 0 || playerTracks.getMorale() == 0) {
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
            freeMovementWasUsed = false;
            additionalActionalActionPointWasUsed = false;
            if (endOfTurnLossCheck()) {
                gameStatus = DEFEAT;
            }
            return true;
        }
        return false;
    }

    private void captureSoldiersProcedure() {
        addMessageLog("Your Soldiers were captured!\n");
        playerTracks.clearRaidedSupplies();
        playerTracks.moveSoldiers(CASTLE);
        playerTracks.reduceMorale();
    }

    private void archersAttack(String selectedTrack) {
        die.roll();

        switch (selectedTrack) {
            case LADDERS:
                currentActionPoints--;
                if (enemyTracks.getLaddersPosition() == 0) {
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(LADDERS);
                    } else {
                        addMessageLog("The attack wasn't effective!");
                    }
                } else {
                    if (die.getValue() > 2) {
                        enemyTracks.moveBackwards(LADDERS);
                    } else {
                        addMessageLog("The attack wasn't effective!");
                    }
                }
                break;
            case BATTERING_RAM:
                currentActionPoints--;
                if (enemyTracks.getRamPosition() == 0) {
                    if (die.getValue() > 4) {
                        enemyTracks.moveBackwards(BATTERING_RAM);
                    } else {
                        addMessageLog("The attack wasn't effective!");
                    }
                } else {
                    if (die.getValue() > 3) {
                        enemyTracks.moveBackwards(BATTERING_RAM);
                    } else {
                        addMessageLog("The attack wasn't effective!");
                    }
                }
                break;
            case SIEGE_TOWER:
                currentActionPoints--;
                if (die.getValue() > 4) {
                    enemyTracks.moveBackwards(SIEGE_TOWER);
                } else {
                    addMessageLog("The attack wasn't effective!");
                }
                break;
        }
    }

    private boolean boilingWaterAttack(String selectedTrack) {
        switch (selectedTrack) {
            case LADDERS:
                if (enemyTracks.getRamPosition() != 1) {
                    addMessageLog("[INVALID ACTION] There are no Ladders in the circle space!");
                    return false;
                }

                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() + 1 > 2) {
                    enemyTracks.moveBackwards(LADDERS);
                } else {
                    addMessageLog("The Boiling Water Attack wasn't effective!");
                }
                break;
            case BATTERING_RAM:
                if (enemyTracks.getRamPosition() != 1) {
                    addMessageLog("[INVALID ACTION] There isn't a Battering Ram in the circle space!");
                    return false;
                }

                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() + 1 > 3) {
                    enemyTracks.moveBackwards(BATTERING_RAM);
                } else {
                    addMessageLog("The Boiling Water Attack wasn't effective!");
                }
                break;
            case SIEGE_TOWER:
                if (enemyTracks.getTowerPosition() != 1) {
                    addMessageLog("[INVALID ACTION] There isn't a Siege Tower in the circle space!");
                    return false;
                }

                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() + 1 > 4) {
                    enemyTracks.moveBackwards(SIEGE_TOWER);
                } else {
                    addMessageLog("The Boiling Water Attack wasn't effective!");
                }
                break;
        }
        boiledWaterWasUsed = true;
        return true;
    }

    private boolean closeCombat(String selectedTrack) {
        switch (selectedTrack) {
            case LADDERS:
                if (enemyTracks.getRamPosition() != 0) {
                    addMessageLog("[INVALID ACTION] There are no Ladders in the close combat area!");
                    return false;
                }

                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() > 4) {
                    enemyTracks.moveBackwards(LADDERS);
                } else {
                    addMessageLog("The attack wasn't effective!");
                }
                break;

            case BATTERING_RAM:
                if (enemyTracks.getRamPosition() != 0) {
                    addMessageLog("[INVALID ACTION] There isn't a Battering Ram in the close combat area!");
                    return false;
                }

                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() > 4) {
                    enemyTracks.moveBackwards(BATTERING_RAM);
                } else {
                    addMessageLog("The attack wasn't effective!");
                }
                break;

            case SIEGE_TOWER:
                if (enemyTracks.getTowerPosition() != 0) {
                    addMessageLog("[INVALID ACTION] There isn't a Siege Tower in the close combat area!");
                    return false;
                }
                currentActionPoints--;
                die.roll();
                if (die.getValue() == 1) {
                    playerTracks.reduceMorale();
                }
                if (die.getValue() > 4) {
                    enemyTracks.moveBackwards(SIEGE_TOWER);
                } else {
                    addMessageLog("The attack wasn't effective!");
                }
                break;
        }
        return true;
    }
// </editor-fold>

// <editor-fold desc="STATE TRANSITION ACTIONS">
    public boolean drawTopCard() {

        //Remove carta do baralho e adiciona ao baralho de removidas        
        Card card = deck.get(0);
        deck.remove(0);
        drawnCards.add(card);

        currentActionPoints = card.getDay(currentDay).getActionPoints();

        addMessageLog("\n" + card.toString() + " was drawn.");

        if (card.getDay(currentDay).isTrebuchetEvent()) {

            switch (enemyTracks.getNumberOfTrebuchets()) {
                case 1:
                    die.roll();
                    if (die.getValue() > 3) {
                        playerTracks.damageWall(1);
                    }
                    break;
                case 2:
                    playerTracks.damageWall(1);
                    break;
                case 3:
                    playerTracks.damageWall(2);
                    break;
            }
        } else {
            ArrayList<String> enemyOrders = card.getDay(currentDay).getEnemyOrders();

            for (String s : enemyOrders) {
                switch (s) {
                    case LADDERS:
                        enemyTracks.moveForward(LADDERS);
                        if (enemyTracks.getLaddersPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        break;

                    case BATTERING_RAM:
                        enemyTracks.moveForward(BATTERING_RAM);
                        if (enemyTracks.getRamPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        break;

                    case SIEGE_TOWER:
                        enemyTracks.moveForward(SIEGE_TOWER);
                        if (enemyTracks.getTowerPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        break;

                    case SLOWEST_UNIT:
                        enemyTracks.moveForward(SLOWEST_UNIT);
                        if (enemyTracks.getLaddersPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        if (enemyTracks.getRamPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        if (enemyTracks.getTowerPosition() == 0) {
                            playerTracks.reduceMorale();
                        }
                        break;
                }
            }
        }

        if (enemyTracks.getNumberOfUnitsInCloseCombat() > 2 || playerTracks.getNumberOfZeroStats() > 1) {
            gameFinish = true;
        }

        return gameFinish;
    }

    public boolean archersAttack() {
        if (currentActionPoints == 0) {

            addMessageLog("[INVALID ACTION] You don't have enough Action Points Available!");

            return false;
        }
        return true;
    }

    public boolean boilingWaterAttack() {
        if (currentActionPoints == 0) {
            addMessageLog("[INVALID ACTION] You don't have enough Action Points Available!");
            return false;
        } else if (boiledWaterWasUsed == true) {
            addMessageLog("[INVALID ACTION] Boiling Water Atack was already used once this turn!");
            return false;
        } else if (enemyTracks.getNumberOfUnitsInCircleSpaces() == 0) {
            addMessageLog("[INVALID ACTION] There are no enemies in the circle spaces!");
            return false;
        }
        return true;
    }

    public boolean attackSelectedTrack(String action, String selectedTrack) {

        switch (action) {
            case ARCHERS:
                archersAttack(selectedTrack);
                return true;
            case BOILING_WATER:
                return boilingWaterAttack(selectedTrack);
            case CLOSE_COMBAT:
                return closeCombat(selectedTrack);
        }
        return false;
    }

    public boolean closeCombat() {
        if (currentActionPoints == 0) {
            addMessageLog("[INVALID ACTION] You don't have enough Action Points Available!");
            return false;
        } else if (enemyTracks.getNumberOfUnitsInCloseCombat() == 0) {
            addMessageLog("[INVALID ACTION] There are no enemies in the close combat area");
            return false;
        }
        return true;
    }

    public void coupure() {
        currentActionPoints--;
        die.roll();
        if (die.getValue() > 4) {
            playerTracks.repairWall();
        }
    }

    public boolean rallyTroopsOptions() {
        if (currentActionPoints == 0) {
            addMessageLog("[INVALID ACTION] You don't have enough Action Points Available!");
            return false;
        }
        return true;
    }

    public void rallyTroops(boolean drm) {
        currentActionPoints--;
        die.roll();
        if (drm) {
            playerTracks.reduceSupplies(1);
            if (die.getValue() + 1 > 4) {
                playerTracks.increaseMorale();
            }
        } else {
            if (die.getValue() > 4) {
                playerTracks.increaseMorale();
            }
        }
    }

    public boolean tunnelMovement() {
        if (currentActionPoints == 0) {
            addMessageLog("[INVALID ACTION] You don't have enough Action Points Available!");
            return false;
        }
        return true;
    }

    public boolean moveSoldiers(int movementType) {
        boolean result;
        switch (movementType) {

            case MOVE_INTO_TUNNEL:
                result = playerTracks.moveIntoTunnel();
                if (result) {
                    currentActionPoints--;
                    freeMovementWasUsed = true;
                }
                return result;
            case FREE_MOVEMENT:
                if (!freeMovementWasUsed) {
                    result = playerTracks.freeMovement();
                    if (result) {
                        freeMovementWasUsed = true;
                    }
                    return result;
                }
                addMessageLog("You can't perform a 'Free Movement', because either a 'Free Movement' or a 'Move Into the Tunnel' "
                        + "were already performed this turn!");
                return false;
            case FAST_MOVEMENT:
                playerTracks.fastMovement();
                currentActionPoints--;
                return true;
        }
        return false;
    }

    public boolean supplyRaid() {
        if (playerTracks.getSoldiersLocation() != ENEMY_LINES) {
            addMessageLog("[INVALID ACTION] Soldiers need to be behind the enemy lines to raid supplies!");
            return false;
        }

        currentActionPoints--;
        die.roll();

        if (die.getValue() == 6) {
            playerTracks.addRaidedSupplies(2);
        } else if (die.getValue() >= 3 && die.getValue() <= 5) {
            playerTracks.addRaidedSupplies(1);
        } else if (die.getValue() == 1) {
            addMessageLog("The supply raid wasn't successful.");
            captureSoldiersProcedure();
        } else {
            addMessageLog("The supply raid wasn't successful.");
        }
        return true;

    }

    public boolean sabotage() {
        if (playerTracks.getSoldiersLocation() != ENEMY_LINES) {
            addMessageLog("[INVALID ACTION] Soldiers need to be behind the enemy lines to sabotage the enemy trebuchets!");
            return false;
        }

        if (enemyTracks.getNumberOfTrebuchets() == 0) {
            addMessageLog("[INVALID ACTION] There are no trebuchets left to destroy!");
            return false;
        }

        currentActionPoints--;
        die.roll();

        switch (die.getValue()) {
            case 5:
            case 6:
                enemyTracks.destroyTrebuchet();
                break;
            case 1:
                addMessageLog("The supply raid wasn't successful.");
                captureSoldiersProcedure();
                break;
            default:
                addMessageLog("The supply raid wasn't successful.");
                break;
        }
        return true;
    }

    public boolean additionalActionPointSelected() {
        if (additionalActionalActionPointWasUsed) {
            addMessageLog("[INVALID ACTION] Aditional Action Points action was already used this turn!");
            return false;
        }

        if (playerTracks.getSupplies() == 0 && playerTracks.getMorale() == 0) {
            addMessageLog("[INVALID ACTION] You don't have enough Morale nor Supply points to perform this action!");
            return false;
        }
        return true;
    }

    public boolean additionalActionPoint(int point) {

        if (point == MORALE) {
            if (playerTracks.getMorale() == 0) {
                addMessageLog("[INVALID ACTION] You don't have enough Morale points to perform this action!");
                return false;
            }
            playerTracks.reduceMorale();
            currentActionPoints++;
            additionalActionalActionPointWasUsed = true;
        } else {
            if (playerTracks.getSupplies() == 0) {
                addMessageLog("[INVALID ACTION] You don't have enough Supply points to perform this action!");
                return false;
            }
            playerTracks.reduceSupplies(1);
            currentActionPoints++;
            additionalActionalActionPointWasUsed = true;
        }
        return true;
    }

    // </editor-fold>
}
