package logic;

import java.io.Serializable;
import static logic.Constants.*;

public class PlayerStats implements Serializable {

    private GameData gameData;
    private int wallStrength;
    private int morale;
    private int supplies;
    private int tunnel;
    private int lastTunnelPosition;
    private int numberOfRaidedSupplies;

    public PlayerStats(GameData gameData) {
        this.gameData = gameData;
        wallStrength = 4;
        morale = 4;
        supplies = 4;
        tunnel = CASTLE;
        lastTunnelPosition = -1;
        numberOfRaidedSupplies = 0;
    }

    // <editor-fold desc="GETTERS">
    public int getWallStrength() {
        return wallStrength;
    }

    public int getMorale() {
        return morale;
    }

    public int getSupplies() {
        return supplies;
    }

    public int getNumberOfRaidedSupplies() {
        return numberOfRaidedSupplies;
    }

    public int getNumberOfZeroStats() {
        int num = 0;

        if (wallStrength == 0) {
            num++;
        }
        if (morale == 0) {
            num++;
        }
        if (supplies == 0) {
            num++;
        }

        return num;
    }

    public int getSoldiersLocation() {
        return tunnel;
    }
    // </editor-fold>

    // <editor-fold desc="SETTERS">
    public void setWallStrength(int wallStrength) {
        this.wallStrength = wallStrength;
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
    // </editor-fold>

    // <editor-fold desc="ACTIONS">
    public void damageWall(int damage) {
        gameData.addMessageLog("The wall has suffered " + damage + " damage point(s).");
        if (wallStrength - damage < 0) {
            wallStrength = 0;
        } else {
            wallStrength -= damage;
        }
    }

    public void reduceMorale() {
        gameData.addMessageLog("You've lost 1 Morale point.");
        if (morale == 0) {
            return;
        }
        morale--;
    }

    public void increaseMorale() {
        gameData.addMessageLog("You've gained 1 Morale point.");
        if (morale == 4) {
            return;
        }
        morale++;
    }

    public void reduceSupplies(int amount) {
        gameData.addMessageLog("You've lost " + amount + " Supplies point(s).");
        if (supplies - amount < 0) {
            supplies = 0;
        } else {
            supplies -= amount;
        }
    }

    public void clearRaidedSupplies() {
        gameData.addMessageLog("Raided supplies were cleared.");
        numberOfRaidedSupplies = 0;
    }

    public void addSupplies(int amount) {
        gameData.addMessageLog("You've gained " + amount + " Supplies point(s).");
        if (supplies + amount > 4) {
            supplies = 4;
        } else {
            supplies += amount;
        }
    }

    public void moveSoldiers(int position) {
        tunnel = position;
        switch (position) {
            case CASTLE:
                gameData.addMessageLog("Soldiers moved back to the castle!");
                break;
            case TUNNEL_CASTLE:
                gameData.addMessageLog("Soldiers moved to the castle side of the tunnel!");
                break;
            case TUNNEL_ENEMY:
                gameData.addMessageLog("Soldiers moved to the enemy lines side of the tunnel!");
                break;
            case ENEMY_LINES:
                gameData.addMessageLog("Soldiers are now behind the enemy lines!");
                break;
        }
    }

    public void repairWall() {
        gameData.addMessageLog("Your wall was repaired in 1 point.");
        if (wallStrength == 4) {
            return;
        }
        wallStrength++;
    }

    public boolean moveIntoTunnel() {

        switch (tunnel) {
            case CASTLE:
                moveSoldiers(TUNNEL_CASTLE);
                lastTunnelPosition = CASTLE;

                return true;
            case ENEMY_LINES:
                moveSoldiers(TUNNEL_ENEMY);
                lastTunnelPosition = ENEMY_LINES;
                return true;
            default:
                gameData.addMessageLog("Soldiers are inside the tunnel already!");
                return false;
        }
    }
    // </editor-fold>
}
