package logic;

import java.io.Serializable;
import logic.states.AwaitBegining;
import logic.states.IStates;

public class GameModel implements Serializable {

    private GameData gameData;
    private IStates state;

    public GameModel() {
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

    public void setState(IStates state) {
        this.state = state;
    }

    //------------------------ Metodos de acesso aos dados/estado do jogo ------------------------
    //------------------------ Metodos de interface de texto ------------------------
    public void startGame() {
        setState(getState().startGame());
    }
    
    public void drawTopCard(){
        setState(getState().drawTopCard());
    }
    
    
    public void archersAttack(){
        setState(getState().archersAttack());
    }
    
    public void attackSelectedTrack(String action, String selectedTrack){
        setState(getState().attackSelectedTrack(action, selectedTrack));
    }
    
    public void boilingWaterAttack(){
        setState(getState().boilingWaterAttack());
    }
    
    public void restartGame(){
        setState(getState().restartGame());
    }
    
    public void closeCombat(){
        setState(getState().closeCombat());
    }
    
    public void coupure(){
        setState(getState().coupure());
    }
    
    public void rallyTroopsOptions(){
        setState(getState().rallyTroopsOptions());
    }
    
    public void rallyTroops(boolean drm){
        setState(getState().rallyTroops(drm));
    }
}
