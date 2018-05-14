package logic.states;

import static logic.Constants.*;
import logic.GameData;

public class AwaitPlayerActionSelection extends StateAdapter {

    public AwaitPlayerActionSelection(GameData g) {
        super(g);
    }

    @Override
    public IStates archersAttack() {

        if (getGame().archersAttack()) {
            return new AwaitTrackSelection(getGame(), ARCHERS);
        }
        return this;
    }

    @Override
    public IStates boilingWaterAttack() {
        if (getGame().boilingWaterAttack()) {
            return new AwaitTrackSelection(getGame(), BOILING_WATER);
        }
        return this;
    }

}
