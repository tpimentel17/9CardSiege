package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.Day;

public class Card3 extends Card implements Serializable{
    
    private ArrayList<String> enemyOrders;
    
    public Card3() {
        super(3);
        
        addDayOne();
        addDayTwo();
        addDayThree();
    }
    
    private void addDayOne(){
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        enemyOrders.add(BATTERING_RAM);
        days.add(new Day(1, 2, enemyOrders));
    }
    
    private void addDayTwo(){
        days.add(new Day(2, 2));
    }
    
    private void addDayThree(){
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        days.add(new Day(3, 2, enemyOrders));
    }
    
}
