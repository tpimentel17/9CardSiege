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

    public int getId() {
        return id;
    }

    public Day getDay(int day) {
        return days.get(day - 1);
    }

    @Override
    public String toString() {
        return "Card #" + id;
    }
    
    

}
