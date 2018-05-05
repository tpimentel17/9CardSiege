package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.Day;

public class Card5 extends Card implements Serializable {

    private ArrayList<String> enemyOrders;

    public Card5() {
        super(5);

        addDayOne();
        addDayTwo();
        addDayThree();
    }

    private void addDayOne() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        days.add(new Day(1, 2, enemyOrders));
    }

    private void addDayTwo() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        enemyOrders.add(BATTERING_RAM);
        days.add(new Day(2, 2, enemyOrders));
    }

    private void addDayThree() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(BATTERING_RAM);
        days.add(new Day(3, 3, enemyOrders));
    }
}
