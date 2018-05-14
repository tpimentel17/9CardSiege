package logic.states;

import static logic.Constants.*;
import logic.GameData;

public class AwaitTopCardToBeDrawn extends StateAdapter {

    public AwaitTopCardToBeDrawn(GameData g) {
        super(g);
    }

    @Override
    public IStates drawTopCard() {
        if (getGame().drawTopCard()) {
            return new AwaitGameFinish(getGame(), DEFEAT);
        } else {
            return new AwaitPlayerActionSelection(getGame());
        }
    }
}
