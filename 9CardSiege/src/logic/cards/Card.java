package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import logic.Day;

public class Card implements Serializable {

    private int id;
    protected ArrayList<Day> days;

    public Card(int id) {
        this.id = id;
        days = new ArrayList<>();
    }
}
