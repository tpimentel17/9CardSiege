package logic;


public interface Constants {
    
    //ENEMY MOVEMENTS
    public static final String LADDERS = "Ladders";
    public static final String BATTERING_RAM = "Ram";
    public static final String SIEGE_TOWER = "Tower";
    public static final String SLOWEST_UNIT = "SlowestUnit";
    
    //TUNNEL POSITIONS [CASTLE | TUNNEL_CASTLE | TUNNEL_ENEMY | ENEMY_LINES]
    public static final int CASTLE = 0;
    public static final int TUNNEL_CASTLE = 1;
    public static final int TUNNEL_ENEMY = 2;
    public static final int ENEMY_LINES = 3;
    
    //ACTIONS
    public static final String ARCHERS = "Archers";
    public static final String BOILING_WATER = "BoilingWater";
    public static final String CLOSE_COMBAT = "CloseCombat";
    
    //GAME STATUS
    public static final int DEFEAT = 0;
    public static final int CONTINUE = 1;
    public static final int VICTORY = 2;
    public static final int DRAW_CARD = 4;
    
    //FILES
    public static final String FILE_TO_SAVE_GAME = "gamesave.bin";

}
