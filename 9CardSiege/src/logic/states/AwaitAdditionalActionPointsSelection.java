package logic.states;

import logic.GameData;

public class AwaitAdditionalActionPointsSelection extends StateAdapter {

    public AwaitAdditionalActionPointsSelection(GameData g) {
        super(g);
    }

    @Override
    public IStates additionalActionPoint(int point) {

        if (getGame().additionalActionPoint(point)) {
            return new AwaitPlayerActionSelection(getGame());
        }
        return this;
    }

}
