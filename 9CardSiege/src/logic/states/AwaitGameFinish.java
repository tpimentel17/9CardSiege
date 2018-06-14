package logic.states;

import logic.GameData;

public class AwaitGameFinish extends StateAdapter {

    int result;

    public AwaitGameFinish(GameData g, int result) {
        super(g);
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Override
    public IStates restartGame() {
        return new AwaitBegining(getGame());
    }
    
        

}
