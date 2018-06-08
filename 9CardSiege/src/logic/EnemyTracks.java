package logic;

import java.io.Serializable;
import static logic.Constants.*;

public class EnemyTracks implements Serializable {

    private GameData gameData;
    private int laddersPosition;        //Strengtth = 2
    private int ramPosition;            //Strengtth = 3
    private int towerPosition;          //Strengtth = 4
    private int numberOfTrebuchets;

    public EnemyTracks(GameData gameData) {
        this.gameData = gameData;
        laddersPosition = 4;
        ramPosition = 4;
        towerPosition = 4;
        numberOfTrebuchets = 3;
    }

    // <editor-fold desc="GETTERS">
    public int getLaddersPosition() {
        return laddersPosition;
    }

    public int getRamPosition() {
        return ramPosition;
    }

    public int getTowerPosition() {
        return towerPosition;
    }

    public int getNumberOfTrebuchets() {
        return numberOfTrebuchets;
    }

    public int getNumberOfUnitsInCloseCombat() {
        int num = 0;

        if (laddersPosition == 0) {
            num++;
        }
        if (ramPosition == 0) {
            num++;
        }
        if (towerPosition == 0) {
            num++;
        }

        return num;
    }

    public int getNumberOfUnitsInCircleSpaces() {
        int num = 0;

        if (laddersPosition == 1) {
            num++;
        }
        if (ramPosition == 1) {
            num++;
        }
        if (towerPosition == 1) {
            num++;
        }

        return num;
    }
    // </editor-fold>

    // <editor-fold desc="PRIVATE METHODS">
    private int getSlowestPosition() {
        int pos = laddersPosition;

        if (ramPosition > pos) {
            pos = ramPosition;
        }
        if (towerPosition > pos) {
            pos = towerPosition;
        }

        return pos;
    }

    // </editor-fold>
    //***************************************************
    public void moveForward(String unit) {
        System.out.println("");
        switch (unit) {
            case LADDERS:
                if (laddersPosition == 0) {
                    break;
                }

                gameData.addMessageLog("Ladders have moved forward 1 unit.");
                laddersPosition--;
                break;

            case BATTERING_RAM:
                if (ramPosition == 0) {
                    break;
                }
                gameData.addMessageLog("Battering Ram has moved forward 1 unit.");
                ramPosition--;
                break;

            case SIEGE_TOWER:
                if (towerPosition == 0) {
                    break;
                }
                gameData.addMessageLog("Siege Tower has moved forward 1 unit.");
                towerPosition--;
                break;

            case SLOWEST_UNIT:
                if (laddersPosition == getSlowestPosition()) {
                    if (laddersPosition == 0) {
                        break;
                    }
                    gameData.addMessageLog("Ladders have moved forward 1 unit.");
                    laddersPosition--;
                }
                if (ramPosition == getSlowestPosition()) {
                    if (ramPosition == 0) {
                        break;
                    }
                    gameData.addMessageLog("Battering Ram has moved forward 1 unit.");
                    ramPosition--;
                }
                if (towerPosition == getSlowestPosition()) {
                    if (towerPosition == 0) {
                        break;
                    }
                    gameData.addMessageLog("Siege Tower has moved forward 1 unit.");
                    towerPosition--;
                }
                break;
        }
    }

    public void moveBackwards(String unit) {
        System.out.println("");
        switch (unit) {
            case LADDERS:
                if (laddersPosition == 4) {
                    break;
                }
                gameData.addMessageLog("Ladders have moved backwards 1 unit.");
                laddersPosition++;
                break;
            case BATTERING_RAM:
                if (ramPosition == 4) {
                    break;
                }
                gameData.addMessageLog("Battering Ram has moved backwards 1 unit.");
                ramPosition++;
                break;
            case SIEGE_TOWER:
                if (towerPosition == 4) {
                    break;
                }
                gameData.addMessageLog("Siege Tower has moved backwards 1 unit.");
                towerPosition++;
                break;
        }
    }

    public void destroyTrebuchet() {
        if (numberOfTrebuchets > 0) {
            numberOfTrebuchets--;
            gameData.addMessageLog("An enemy trebuchet was destroyed!");
        }
    }

}
