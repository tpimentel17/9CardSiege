package logic;

import java.util.Observable;
import logic.states.IStates;

public class ObservableGame extends Observable {

    GameModel gameModel;

    public ObservableGame() {
        gameModel = new GameModel();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;

        setChanged();
        notifyAll();
    }

    // -----------------------------------  Métodos de acesso aos dados/estado do jogo ----------------------------------- 
    public GameData getGameData() {
        return gameModel.getGameData();
    }

    public IStates getState() {
        return gameModel.getState();
    }

    public boolean boiledWaterWasUsed() {
        return gameModel.boiledWaterWasUsed();
    }

    public boolean freeMovementWasUsed() {
        return gameModel.freeMovementWasUsed();
    }

    public boolean additionalActionalActionPointWasUsed() {
        return gameModel.additionalActionalActionPointWasUsed();
    }

    public PlayerTracks getPlayerTracks() {
        
        return gameModel.getPlayerTracks();
        
    }

    public EnemyTracks getEnemyTracks() {
        return gameModel.getEnemyTracks();
    }

    // ----------------------------------- Transição de estados -----------------------------------
    public void startGame() {
        gameModel.startGame();

        setChanged();
        notifyObservers();
    }

    public void drawTopCard() {
        gameModel.drawTopCard();

        setChanged();
        notifyObservers();
    }

    public void archersAttack() {
        gameModel.archersAttack();

        setChanged();
        notifyObservers();
    }

    public void attackSelectedTrack(String selectedTrack) {
        gameModel.attackSelectedTrack(selectedTrack);

        setChanged();
        notifyObservers();
    }

    public void boilingWaterAttack() {
        gameModel.boilingWaterAttack();

        setChanged();
        notifyObservers();
    }

    public void restartGame() {
        gameModel.restartGame();

        setChanged();
        notifyObservers();
    }

    public void closeCombat() {
        gameModel.closeCombat();

        setChanged();
        notifyObservers();
    }

    public void coupure() {
        gameModel.coupure();

        setChanged();
        notifyObservers();

    }

    public void rallyTroopsOptions() {
        gameModel.rallyTroopsOptions();

        setChanged();
        notifyObservers();
    }

    public void rallyTroops(boolean drm) {
        gameModel.rallyTroops(drm);

        setChanged();
        notifyObservers();
    }

    public void tunnelMovement() {
        gameModel.tunnelMovement();

        setChanged();
        notifyObservers();
    }

    public void moveSoldiers(int movementType) {
        gameModel.moveSoldiers(movementType);

        setChanged();
        notifyObservers();
    }

    public void supplyRaid() {
        gameModel.supplyRaid();

        setChanged();
        notifyObservers();
    }

    public void sabotage() {
        gameModel.sabotage();

        setChanged();
        notifyObservers();
    }

    public void additionalActionPointsSelected() {
        gameModel.additionalActionPointsSelected();

        setChanged();
        notifyObservers();
    }

    public void additionalActionPoint(int point) {
        gameModel.additionalActionPoint(point);

        setChanged();
        notifyObservers();
    }

}
