package logic;

import java.io.Serializable;

public class Die implements Serializable {

    private int value;
    private GameData gameData;

    public Die(GameData gameData) {
        this.gameData = gameData;
        value = (int) (Math.random() * (6) + 1);    //((max-min)+min)+1
    }

    public int getValue() {
        return value;
    }
    
    public void roll() {
        value = (int) (Math.random() * (6) + 1);    //((max-min)+min)+1
        gameData.addMessageLog("A " + value +" was rolled.");

    }
}
