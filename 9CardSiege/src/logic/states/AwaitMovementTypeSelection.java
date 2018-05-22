package logic.states;

import static logic.Constants.*;
import logic.GameData;

public class AwaitMovementTypeSelection extends StateAdapter {
        
    public AwaitMovementTypeSelection(GameData g) {
        super(g);
    }
    
    @Override
    public IStates moveSoldiers(int movementType) {
        if (getGame().moveSoldiers(movementType)) {
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
