package logic;

import java.io.Serializable;
import static logic.Constants.*;

public class PlayerStats implements Serializable {

    private int wallStrength;
    private int morale;
    private int supplies;
    private int tunnel;
    private int numberOfRaidedSupplies;

    public PlayerStats() {
        wallStrength = 4;
        morale = 4;
        supplies = 4;
        tunnel = CASTLE;
        numberOfRaidedSupplies = 0;
    }
    
    //GETTERS
    
    //SETTERS

}
