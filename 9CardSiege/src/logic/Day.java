package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {

    private int day;
    private int actionPoints;
    private String[] enemyOrders;

    public Day(int day, int actionPoints, String[] enemyAdvancementOrders) {
        this.day = day;
        this.actionPoints = actionPoints;
        this.enemyOrders = enemyAdvancementOrders;
    }
}
