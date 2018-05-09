package ui.txt;

import java.util.Scanner;
import static logic.Constants.*;
import logic.GameModel;
import logic.states.*;
import logic.states.IStates;

public class TextUI {

    private GameModel gameModel;
    private boolean quit = false;

    public TextUI(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void uiAwaitBeginning() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;

        System.out.println("\n=== AWAITING FOR THE BEGINNING OF THE GAME ===\n");

        do {

            System.out.println();
            System.out.println("1 - Start a new game");
            System.out.println("2 - Continue a previous game");
            System.out.println("3 - Quit");
            System.out.println();
            System.out.print("> ");

            option = sc.next();

            if (option.length() >= 1) {
                c = option.charAt(0);
            } else {
                c = ' ';
            }

        } while (c < '1' || c > '3');

        switch (c) {
            case '1':
                gameModel.startGame();
                break;
            case '2':
                //Falta implementar <------------------------------------------------
                break;
            case '3':
                quit = true;
                return;
        }
    }

    public void uiAwaitTopCardToBeDrawn() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;

        System.out.println();
        System.out.println();
        System.out.println("*********************************************");
        System.out.println();
        System.out.println("\n=== DRAW CARD FROM DECK ===\n");

        do {

            System.out.println();
            System.out.println("1 - Draw Card");
            System.out.println("2 - Show Status Tracks");
            System.out.println("3 - Show Enemy Tracks");
            System.out.println("4 - Save game");
            System.out.println("5 - Quit");
            System.out.println();
            System.out.print("> ");

            option = sc.next();

            if (option.length() >= 1) {
                c = option.charAt(0);
            } else {
                c = ' ';
            }

        } while (c < '1' || c > '5');

        switch (c) {
            case '1':
                gameModel.drawTopCard();
                break;
            case '2':
                showStatusTracks();
                break;
            case '3':
                showEnemyTracks();
                break;
            case '4':
                //Falta implementar <------------------------------------------------
                break;
            case '5':
                quit = true;
                return;
        }
    }

    public void uiAwaitPlayerActionSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("*********************************************");
        System.out.println();
        System.out.println("\n=== SELECT AN ACTION ===\n");
        
        do {

            System.out.println();
            System.out.println("1 - Draw Card");
            System.out.println("2 - Show Status Tracks");
            System.out.println("3 - Show Enemy Tracks");
            System.out.println("4 - Quit");
            System.out.println();
            System.out.print("> ");

            option = sc.next();

            if (option.length() >= 1) {
                c = option.charAt(0);
            } else {
                c = ' ';
            }

        } while (c < '1' || c > '4');

        switch (c) {
            case '1':
                
                break;
            case '2':
                showStatusTracks();
                break;
            case '3':
                showEnemyTracks();
                break;
            case '4':
                quit = true;
                return;
        }
    }

    public void uiAwaitGameFinish() {
        System.out.println();
        System.out.println();
        System.out.println("*********************************************");
        System.out.println();
        System.out.println("\n=== GG NOOB ===\n");

    }

    public void run() {
        while (!quit) {
            IStates state = gameModel.getState();

            if (state instanceof AwaitBegining) {
                uiAwaitBeginning();
            } else if (state instanceof AwaitTopCardToBeDrawn) {
                uiAwaitTopCardToBeDrawn();
            } else if (state instanceof AwaitPlayerActionSelection) {
                uiAwaitPlayerActionSelection();
            } else if (state instanceof AwaitGameFinish) {
                uiAwaitGameFinish();
            }

        }
    }
    //--------------------------Private methods--------------------------

    private void showStatusTracks() {
        System.out.println();
        System.out.println("****Status Tracks****");
        System.out.println();
        System.out.println("Wall Strength -> " + gameModel.getGameData().getPlayerStats().getWallStrength());
        System.out.println("Morale -> " + gameModel.getGameData().getPlayerStats().getMorale());
        System.out.println("Supplies -> " + gameModel.getGameData().getPlayerStats().getSupplies());
        System.out.println();
        System.out.println("Soldiers Position:");

        switch (gameModel.getGameData().getPlayerStats().getTunnel()) {
            case CASTLE:
                System.out.println("Castle [x| : | ] EnemyLines");
                break;
            case TUNNEL_CASTLE:
                System.out.println("Castle [ |x: | ] EnemyLines");
                break;
            case TUNNEL_ENEMY:
                System.out.println("Castle [ | :x| ] EnemyLines");
                break;
            case ENEMY_LINES:
                System.out.println("Castle [ | : |x] EnemyLines");
                break;
        }
        System.out.print("Raided Supplies -> " + gameModel.getGameData().getPlayerStats().getNumberOfRaidedSupplies());
    }

    private void showEnemyTracks() {

        int laddersPosition = gameModel.getGameData().getEnemyTracks().getLaddersPosition();
        int ramPosition = gameModel.getGameData().getEnemyTracks().getRamPosition();
        int towerPosition = gameModel.getGameData().getEnemyTracks().getTowerPosition();

        int closeCombatUnits = 0;

        if (laddersPosition == 0) {
            closeCombatUnits++;
        } else if (ramPosition == 0) {
            closeCombatUnits++;
        } else if (towerPosition == 0) {
            closeCombatUnits++;
        }

        System.out.println();
        System.out.println("****Enemy Tracks****");
        System.out.println();
        System.out.println("Close combat units -> " + closeCombatUnits + "\t\tStrength: 4 (each)");
        System.out.println("Ladders distance -> " + laddersPosition + "\t\tStrength: 2");
        System.out.println("Battering Ram distance -> " + ramPosition + "\tStrength: 3");
        System.out.println("Siege Tower distance -> " + towerPosition + "\tStrength: 4");
        System.out.println("Number of Trebuchet(s) -> " + gameModel.getGameData().getEnemyTracks().getNumberOfTrebuchets());
    }

}
