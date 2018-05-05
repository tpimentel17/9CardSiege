package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.Day;

public class Card6 extends Card implements Serializable{
    private ArrayList<String> enemyOrders;
    
    public Card6() {
        super(6);
        
        addDayOne();
        addDayTwo();
        addDayThree();
    }
    
    private void addDayOne(){
        enemyOrders = new ArrayList<>();
        enemyOrders.add(BATTERING_RAM);
        enemyOrders.add(SIEGE_TOWER);
        days.add(new Day(1, 3, enemyOrders));
    }
    
    private void addDayTwo(){
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        days.add(new Day(2, 3, enemyOrders));
    }
    
    private void addDayThree(){
        enemyOrders = new ArrayList<>();
        enemyOrders.add(SLOWEST_UNIT);
        days.add(new Day(3, 3, enemyOrders));
    }
    
}
