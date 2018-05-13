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
        
        System.out.println("\n\n***************************************************************");
        System.out.println("[INVALID ACTION] You don't have enough Action Points Available!");
        System.out.println("***************************************************************");
        return this;
    }
}
