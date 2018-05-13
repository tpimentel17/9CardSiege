package logic.states;

import logic.GameData;

public class AwaitTrackSelection extends StateAdapter {

    private String action;

    public AwaitTrackSelection(GameData g, String action) {
        super(g);
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public IStates attackSelectedTrack(String action, String selectedTrack) {
        if(getGame().attackSelectedTrack(action, selectedTrack))
            return new AwaitPlayerActionSelection(getGame());
        return this;
    }

    
        
   
    
   
}
