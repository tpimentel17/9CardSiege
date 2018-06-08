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

    @Override
    public IStates closeCombat() {
        if (getGame().closeCombat()) {
            return new AwaitTrackSelection(getGame(), CLOSE_COMBAT);

        }
        return this;
    }

    @Override
    public IStates coupure() {
        getGame().coupure();
        switch (getGame().checkGameStatus()) {
            case DRAW_CARD:
                getGame().setDefaultStatus();
                return new AwaitTopCardToBeDrawn(getGame());
            case VICTORY:
                return new AwaitGameFinish(getGame(), VICTORY);
            case DEFEAT:
                return new AwaitGameFinish(getGame(), DEFEAT);
            case CONTINUE:
                return this;
        }
        return this;
    }

    @Override
    public IStates rallyTroopsOptions() {
        if (getGame().rallyTroopsOptions()) {
            return new AwaitOptionSelection(getGame());
        }
        return this;
    }

    @Override
    public IStates tunnelMovement() {
        if (getGame().tunnelMovement()) {
            return new AwaitMovementTypeSelection(getGame());
        }
        return this;
    }

    @Override
    public IStates supplyRaid() {
        if (getGame().supplyRaid()) {
            switch (getGame().checkGameStatus()) {
                case DRAW_CARD:
                    getGame().setDefaultStatus();
                    return new AwaitTopCardToBeDrawn(getGame());
                case VICTORY:
                    return new AwaitGameFinish(getGame(), VICTORY);
                case DEFEAT:
                    return new AwaitGameFinish(getGame(), DEFEAT);
                case CONTINUE:
                    return this;
            }
        }
        return this;
    }

    @Override
    public IStates sabotage() {
        if (getGame().sabotage()) {
            switch (getGame().checkGameStatus()) {
                case DRAW_CARD:
                    getGame().setDefaultStatus();
                    return new AwaitTopCardToBeDrawn(getGame());
                case VICTORY:
                    return new AwaitGameFinish(getGame(), VICTORY);
                case DEFEAT:
                    return new AwaitGameFinish(getGame(), DEFEAT);
                case CONTINUE:
                    return this;
            }
        }
        return this;
    }

}
