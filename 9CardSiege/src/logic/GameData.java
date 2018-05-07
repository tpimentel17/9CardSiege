package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import logic.cards.*;

public class GameData implements Serializable {

    private PlayerStats playerStats;
    private EnemyTracks enemyTracks;
    private Die die;
    private ArrayList<Card> deck;
    private ArrayList<Card> drawnCards;

    public GameData() {
        playerStats = new PlayerStats();
        enemyTracks = new EnemyTracks();
        die = new Die();
        deck = new ArrayList<>(7);
        drawnCards = new ArrayList<>(7);
    }

    public boolean initialize() {
        deck.add(new Card1());
        deck.add(new Card2());
        deck.add(new Card3());
        deck.add(new Card4());
        deck.add(new Card5());
        deck.add(new Card6());
        deck.add(new Card7());
        Collections.shuffle(deck);
        
        return true;
    }
}
