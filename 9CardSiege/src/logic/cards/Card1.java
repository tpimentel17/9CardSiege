package logic.cards;

import java.io.Serializable;
import java.util.ArrayList;
import static logic.Constants.*;
import logic.Day;

public class Card1 extends Card implements Serializable {
    
    public Card1(){
        super(1);
        
        addDayOne();
        addDayTwo();
        addDayThree();
    }
    
    private void addDayOne(){
        days.add(new Day(1, 1));
    }
    
    private void addDayTwo(){
        days.add(new Day(2, 2));
    }
    
    private void addDayThree(){
        days.add(new Day(3, 3));
    }
    
}
