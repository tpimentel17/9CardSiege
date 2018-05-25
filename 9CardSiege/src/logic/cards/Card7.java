package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import logic.*;
import static logic.Constants.*;

public class Card7 extends Card implements Serializable {

    private ArrayList<String> enemyOrders;

    public Card7() {
        super(7);

        addDayOne();
        addDayTwo();
        addDayThree();
    }

    private void addDayOne() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(BATTERING_RAM);
        days.add(new Day(1, 2, enemyOrders));
    }

    private void addDayTwo() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(SIEGE_TOWER);
        days.add(new Day(2, 2, enemyOrders));
    }

    private void addDayThree() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(LADDERS);
        enemyOrders.add(BATTERING_RAM);
        enemyOrders.add(SIEGE_TOWER);
        days.add(new Day(3, 3, enemyOrders));
    }
}
