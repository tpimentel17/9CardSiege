package logic;

import java.io.Serializable;
import static logic.Constants.*;

public class EnemyTracks implements Serializable {

    private int laddersPosition;        //Strengtth = 2
    private int ramPosition;            //Strengtth = 3
    private int towerPosition;          //Strengtth = 4
    private int numberOfTrebuchets;

    public EnemyTracks() {
        laddersPosition = 4;
        ramPosition = 4;
        towerPosition = 4;
        numberOfTrebuchets = 3;
    }

    //GETTERS
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

    //SETTERS
    public void setLaddersPosition(int laddersPosition) {
        this.laddersPosition = laddersPosition;
    }

    public void setRamPosition(int ramPosition) {
        this.ramPosition = ramPosition;
    }

    public void setTowerPosition(int towerPosition) {
        this.towerPosition = towerPosition;
    }

    public void setNumberOfTrebuchets(int numberOfTrebuchets) {
        this.numberOfTrebuchets = numberOfTrebuchets;
    }

    //PRIVATE METHODS
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

    //***************************************************
    public void moveForward(String unit) {
        switch (unit) {
            case LADDERS:
                laddersPosition--;
                break;
            case BATTERING_RAM:
                ramPosition--;
                break;
            case SIEGE_TOWER:
                towerPosition--;
                break;
            case SLOWEST_UNIT:
                if (laddersPosition == getSlowestPosition()) {
                    laddersPosition--;
                }
                if (ramPosition == getSlowestPosition()) {
                    ramPosition--;
                }
                if (towerPosition == getSlowestPosition()) {
                    towerPosition--;
                }
                break;
        }
    }

}
