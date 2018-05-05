package logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {

    private int day;
    private int actionPoints;
    private ArrayList<String> enemyOrders;
            
    public Day(int day, int actionPoints, ArrayList<String> enemyOrders) {
        this.day = day;
        this.actionPoints = actionPoints;
        this.enemyOrders = new ArrayList<>();
        this.enemyOrders.addAll(enemyOrders);
    }
    
    public Day(int day, int actionPoints) {
        this.day = day;
        this.actionPoints = actionPoints;
        this.enemyOrders = new ArrayList<>();
    }
}
