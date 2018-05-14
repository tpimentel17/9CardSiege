package logic.states;

import static logic.Constants.*;
import logic.GameData;

public class AwaitOptionSelection extends StateAdapter {

    public AwaitOptionSelection(GameData g) {
        super(g);
    }

    @Override
    public IStates rallyTroops(boolean drm) {
        getGame().rallyTroops(drm);
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
        return this;
    }

}
