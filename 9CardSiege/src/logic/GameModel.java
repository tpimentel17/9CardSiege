package logic;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.states.AwaitBegining;
import logic.states.AwaitTrackSelection;
import logic.states.IStates;

public class GameModel implements Serializable {

    private GameData gameData;
    private IStates state;

    public GameModel() {
        gameData = new GameData();
        state = new AwaitBegining(gameData);
    }

    // <editor-fold desc="GETTERS">
    public GameData getGameData() {
        return gameData;
    }

    public IStates getState() {
        return state;
    }
    // </editor-fold>

    // <editor-fold desc="SETTERS">
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void setState(IStates state) {
        this.state = state;
    }
    // </editor-fold>
    
    // <editor-fold desc="METODOS DE ACESSO AOS DADOS/ESTADO DO JOGO">
   
    
    public boolean boiledWaterWasUsed() {
        return gameData.boiledWaterWasUsed();
    }

    public boolean freeMovementWaterWasUsed() {
        return gameData.freeMovementWaterWasUsed();
    }

    public boolean additionalActionalActionPointWasUsed() {
        return gameData.additionalActionalActionPointWasUsed();
    }
    
    public PlayerTracks getPlayerTracks(){
        return gameData.getPlayerTracks();
    }
    
    public EnemyTracks getEnemyTracks(){
        return gameData.getEnemyTracks();
    }
    
    public ArrayList<String> getMessageLog(){
        return gameData.getMessageLog();
    }
    
    public void clearMessageLog(){
        gameData.clearMessageLog();
    }
    
    
    // ----------------------------------- Transição de estados ----------------------------------- 
    public void startGame() {
        setState(getState().startGame());
    }

    public void drawTopCard() {
        setState(getState().drawTopCard());
    }

    public void archersAttack() {
        setState(getState().archersAttack());
    }

    public void attackSelectedTrack(String selectedTrack) {
        String action = ((AwaitTrackSelection)getState()).getAction();
        
        setState(getState().attackSelectedTrack(action, selectedTrack));
    }

    public void boilingWaterAttack() {
        setState(getState().boilingWaterAttack());
    }

    public void restartGame() {
        setState(getState().restartGame());
    }

    public void closeCombat() {
        setState(getState().closeCombat());
    }

    public void coupure() {
        setState(getState().coupure());
    }

    public void rallyTroopsOptions() {
        setState(getState().rallyTroopsOptions());
    }

    public void rallyTroops(boolean drm) {
        setState(getState().rallyTroops(drm));
    }

    public void tunnelMovement() {
        setState(getState().tunnelMovement());
    }
    
    public void moveSoldiers(int movementType){
        setState(getState().moveSoldiers(movementType));
    }
    
    public void supplyRaid(){
        setState(getState().supplyRaid());
    }
    
    public void sabotage(){
        setState(getState().sabotage());
    }
    
    public void additionalActionPointsSelected(){
        setState(getState().additionalActionPointsSelected());
    }
    
    public void additionalActionPoint(int point){
        setState(getState().additionalActionPoint(point));
    }
    // </editor-fold>
}
