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

    //CARD DIMENSIONS
    public static final int CARD_WIDTH = 340;
    public static final int CARD_HEIGHT = 480;
    public static final int CARD_WIDTH_GAP = 20;
    public static final int CARD_COL_DISPLACEMENT = 110;
    public static final int CARD_ROW_DISPLACEMENT = 80;

    //PLAYER TRACKS
    public static final int PLAYER_COL_ANCHOR = 75;
    public static final int PLAYER_ROW_ANCHOR = 100;

    public static final int PLAYER_TUNNEL_COL_ANCHOR = 65;
    public static final int PLAYER_TUNNEL_ROW_ANCHOR = 495;
    public static final int TUNNEL_COL_DISPLACEMENT = 53;

    public static final int RAIDED_SUPPLIES_COL = PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT * 2 + 8;
    public static final int RAIDED_SUPPLIES_ROW = PLAYER_TUNNEL_ROW_ANCHOR + 5;
    public static final int RAIDED_SUPPLIES_ROW_DISPLACEMENT = 58;

    //ENEMY TRACKS
    public static final int TREBUCHET_COL_ANCHOR = RAIDED_SUPPLIES_COL + 148;
    public static final int TREBUCHET_ROW_ANCHOR = RAIDED_SUPPLIES_ROW;
    public static final int TREBUCHET_COL_DISPLACEMENT = 115;

    public static final int ENEMY_COL_ANCHOR = TREBUCHET_COL_ANCHOR;
    public static final int ENEMY_ROW_ANCHOR = TREBUCHET_ROW_ANCHOR - 92;
    public static final int ENEMY_COL_DISPLACEMENT = CARD_COL_DISPLACEMENT + 6;
    
    public static final int CLOSE_COMBAT_ROW_DISPLACEMENT = 72;
    public static final int CLOSE_COMBAT_COL_DISPLACEMENT = 25;

    //DICE
    public static final int DICE_SIZE = 100;

    //CENTER PANELS HEIGHT
    public static final int CENTER_PANEL_HEIGHT = 3 * BORDER_Y_GAP + CARD_HEIGHT + DICE_SIZE;

    //TRACK PANEL DIMENSIONS
    public static final int TRACK_WIDTH = 2 * CARD_WIDTH + 3 * BORDER_X_GAP;

    //DECK PANEL DIMENSIONS
    public static final int DECK_WIDTH = CARD_WIDTH + 2 * BORDER_X_GAP;

    //ACTIONS PANEL DIMENSIONS
    public static final int ACTIONS_WIDTH = 2 * BORDER_X_GAP + 350;

    //TOKEN DIMENSIONS
    public static final int TOKEN_RADIUS = 30;

    //WINDOW DIMENSIONS
    public static final int FRAME_WIDTH = ACTIONS_WIDTH + DECK_WIDTH + TRACK_WIDTH + 24;
    public static final int FRAME_HEIGHT = 950;
}
