package logic;

import java.util.Observable;

public class ObservableGame extends Observable {
    
    GameModel gameModel;
    
    public ObservableGame(){
        gameModel = new GameModel();
    }
    
    public GameModel getGameModel(){
        return gameModel;
    }
    
    
}
