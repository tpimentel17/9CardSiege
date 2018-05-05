package logic;

import java.io.Serializable;

public class EnemyTracks implements Serializable {
    private int laddersPosition;        //Strengtth = 2
    private int ramPosition;            //Strengtth = 3
    private int towerPosition;          //Strengtth = 4
    private int numberOfTrebuchets;
    
    public EnemyTracks(){       
        laddersPosition = 4;    
        ramPosition = 4;        
        towerPosition = 4;      
        numberOfTrebuchets = 3;
    }
    
    //GETTERS
    
    //SETTERS
}
