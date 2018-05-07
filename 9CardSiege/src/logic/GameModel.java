package logic;

import java.io.Serializable;
import logic.states.AwaitBegining;
import logic.states.IStates;

public class GameModel implements Serializable{
    
    private GameData gameData;
    private IStates state;
    
    public GameModel(){
        gameData = new GameData();
        state = new AwaitBegining(gameData);
    }
    
    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public IStates getState() {
        return state;
    }
}

