package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.Day;

public class Card2 extends Card implements Serializable {

    private ArrayList<String> enemyOrders;

    public Card2() {
        super(2);

        addDayOne();
        addDayTwo();
        addDayThree();
    }

    private void addDayOne() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(SIEGE_TOWER);
        days.add(new Day(1, 2, enemyOrders));
    }

    private void addDayTwo() {
        enemyOrders = new ArrayList<>();
        enemyOrders.add(SLOWEST_UNIT);
        days.add(new Day(2, 2, enemyOrders));
    }

    private void addDayThree() {
        days.add(new Day(3, 1));
    }

}
