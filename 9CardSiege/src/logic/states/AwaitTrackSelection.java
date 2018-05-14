package logic.states;

import static logic.Constants.*;
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
        if (getGame().attackSelectedTrack(action, selectedTrack)) {
            switch (getGame().checkGameStatus()) {
                case DRAW_CARD:
                    getGame().setDefaultStatus();
                    return new AwaitTopCardToBeDrawn(getGame());
                case VICTORY:
                    return new AwaitGameFinish(getGame(), VICTORY);
                case DEFEAT:
                    return new AwaitGameFinish(getGame(), DEFEAT);
                case CONTINUE:
                    return new AwaitPlayerActionSelection(getGame());          
            }
        }
        return this;
    }

}
