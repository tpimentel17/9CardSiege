package logic.states;

import logic.GameData;

public class StateAdapter implements IStates {

    private GameData game;

    public StateAdapter(GameData g) {
        this.game = g;
    }

    public GameData getGame() {
        return game;
    }

    public void setGame(GameData game) {
        this.game = game;
    }

    @Override
    public IStates startGame() {
        return this;
    }

    @Override
    public IStates drawTopCard() {
        return this;
    }
}
