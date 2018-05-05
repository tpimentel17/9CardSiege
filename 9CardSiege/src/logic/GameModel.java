package logic;

import java.io.Serializable;
import logic.states.AwaitBegining;
import logic.states.IStates;

public class GameModel implements Serializable{
    
    private GameData gameData;
    private IStates state;
    
    public GameModel(){
        gameData = new GameData();
        state = new AwaitBegining();
    }
    
    public IStates getState(){
        return state;
    }
}

