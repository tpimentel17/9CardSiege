package logic;

import java.util.Observable;
import logic.states.IStates;

public class ObservableGame extends Observable {
    
    GameModel gameModel;
    
    public ObservableGame(){
        gameModel = new GameModel();
    }
    
    public GameModel getGameModel(){
        return gameModel;
    }
    
    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
        
        setChanged();
        notifyAll();
    }
    
    public GameData getGameData(){
        return gameModel.getGameData();
    }
    
    public IStates getState(){
        return gameModel.getState();
    }
}
