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

    //MOVEMENT TYPES
    public static final int MOVE_INTO_TUNNEL = 0;
    public static final int FREE_MOVEMENT = 1;
    public static final int FAST_MOVEMENT = 2;

    //PLAYER POINTS
    public static final int MORALE = 1;
    public static final int SUPPLY = 2;

    //BORDER GAPS
    public static final int BORDER_X_GAP = 30;
    public static final int BORDER_Y_GAP = 60;

    //WINDOW DIMENSIONS
    public static final int FRAME_WIDTH = 1300;
    public static final int FRAME_HEIGHT = 800;

    //CARD DIMENSIONS
    public static final int CARD_WIDTH = 340;
    public static final int CARD_HEIGHT = 480;
    public static final int CARD_WIDTH_GAP = 20;
    public static final int CARD_COL_DISPLACEMENT = 110;
    public static final int CARD_ROW_DISPLACEMENT = 80;

    //PLAYER TRACKS
    public static final int PLAYER_COL_ANCHOR = 75;
    public static final int PLAYER_ROW_ANCHOR = 100;

    //DICE
    public static final int DICE_SIZE = 100;

    //ENEMY TRACKS
    
    //CENTER PANELS HEIGHT
    public static final int CENTER_PANEL_HEIGHT = 3 * BORDER_Y_GAP + CARD_HEIGHT + DICE_SIZE;

    //TRACK PANEL DIMENSIONS
    public static final int TRACK_WIDTH = 2 * CARD_WIDTH + 3 * BORDER_X_GAP;

    //DECK PANEL DIMENSIONS
     public static final int DECK_WIDTH = CARD_WIDTH + 2 * BORDER_X_GAP;   
     
    //TOKEN DIMENSIONS
    public static final int TOKEN_RADIUS = 30;
}
