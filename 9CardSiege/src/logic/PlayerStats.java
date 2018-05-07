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
    public int getWallStrength() {
        return wallStrength;
    }

    public int getMorale() {
        return morale;
    }

    public int getSupplies() {
        return supplies;
    }

    public int getTunnel() {
        return tunnel;
    }

    public int getNumberOfRaidedSupplies() {
        return numberOfRaidedSupplies;
    }

    //SETTERS
    public void setWallStrength(int wallStrength) {
        this.wallStrength = wallStrength;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public void setSupplies(int supplies) {
        this.supplies = supplies;
    }

    public void setTunnel(int tunnel) {
        this.tunnel = tunnel;
    }

    public void setNumberOfRaidedSupplies(int numberOfRaidedSupplies) {
        this.numberOfRaidedSupplies = numberOfRaidedSupplies;
    }
    

}
