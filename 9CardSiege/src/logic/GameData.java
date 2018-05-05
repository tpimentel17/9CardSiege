package logic;

import java.io.Serializable;

public class GameData implements Serializable {

    private PlayerStats playerStats;
    private EnemyTracks enemyTracks;
    private Die die;

    public GameData() {
        playerStats = new PlayerStats();
        enemyTracks = new EnemyTracks();
        die = new Die();
    }
}
