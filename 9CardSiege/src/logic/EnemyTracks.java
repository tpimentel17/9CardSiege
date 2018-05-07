package logic;

import java.io.Serializable;

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
}
