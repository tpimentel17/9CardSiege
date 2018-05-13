package logic;

import java.io.Serializable;

public class Die implements Serializable {

    private int value;

    public Die() {
        value = (int) (Math.random() * (6) + 1);    //((max-min)+min)+1
    }

    public int getValue() {
        return value;
    }
    
    public void roll() {
        value = (int) (Math.random() * (6) + 1);    //((max-min)+min)+1
        System.out.println("A " + value +" was rolled.");
    }
}
