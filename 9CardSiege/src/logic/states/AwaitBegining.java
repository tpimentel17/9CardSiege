
package logic.states;

import logic.GameData;

public class AwaitBegining extends StateAdapter {
    
    public AwaitBegining(GameData g) {
        super(g);
    }

    @Override
    public IStates startGame() {
        if(getGame().initialize())
            return new AwaitBegining(getGame());
        return this;
    }
    
    
    
    
    
}
