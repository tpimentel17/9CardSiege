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
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println("\n=== DRAW CARD FROM DECK - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");

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
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println("\n=== SELECT AN ACTION - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");

        do {
            System.out.println();
            System.out.println("Available Action Points: " + gameModel.getGameData().getCurrentActionPoints());
            System.out.println();
            System.out.println("1 - Archers Attack");
            System.out.println("2 - Boiling Water Attack");
            System.out.println("3 - Close Combat Attack");
            System.out.println("4 - Coupure");
            System.out.println("5 - Rally Troops");
            System.out.println("6 - Tunnel Movement");
            System.out.println("7 - Supply Raid");
            System.out.println("8 - Sabotage");
            System.out.println("9 - Quit");

            System.out.println();
            System.out.print("> ");

            option = sc.next();

            if (option.length() >= 1) {
                c = option.charAt(0);
            } else {
                c = ' ';
            }

        } while (c < '1' || c > '9');

        switch (c) {
            case '1':
                gameModel.archersAttack();
                break;
            case '2':
                gameModel.boilingWaterAttack();
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            case '7':
                break;
            case '8':
                break;
            case '9':
                quit = true;
                return;
        }
    }

    public void uiAwaitTrackSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;

        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");

        if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(ARCHERS)) {
            System.out.println("\n=== SELECT THE TRACK TO BE ATTACKED BY THE ARCHERS - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        } else {
            System.out.println("\n=== SELECT THE TRACK TO BE ATTACKED WITH BOILING WATER - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        }

        do {
            System.out.println();
            System.out.println("1 - Ladders");
            System.out.println("2 - Battering Ram");
            System.out.println("3 - Siege Tower");
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
                if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(ARCHERS)) {
                    gameModel.attackSelectedTrack(ARCHERS, LADDERS);
                } else {
                    gameModel.attackSelectedTrack(BOILING_WATER, LADDERS);
                }
                break;
            case '2':
                if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(ARCHERS)) {
                    gameModel.attackSelectedTrack(ARCHERS, BATTERING_RAM);
                } else {
                    gameModel.attackSelectedTrack(BOILING_WATER, BATTERING_RAM);
                }
                break;
            case '3':
                if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(ARCHERS)) {
                    gameModel.attackSelectedTrack(ARCHERS, SIEGE_TOWER);
                } else {
                    gameModel.attackSelectedTrack(BOILING_WATER, SIEGE_TOWER);
                }
                break;
        }
    }

    public void uiAwaitGameFinish() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println("\n=== O JOGO TERMINOU ===\n");
        System.out.println();
        if (((AwaitGameFinish) gameModel.getState()).getResult() == VICTORY) {
            System.out.println("\n-------------> PARABÉNS GANHOU! <-------------");
        } else {
            System.out.println("\n-------------> NÃO FOI DESTA QUE VENCEU <-------------");
        }
        
        do {

            System.out.println();
            System.out.println("Deseja começar um novo jogo?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.println();
            System.out.print("> ");

            option = sc.next();

            if (option.length() >= 1) {
                c = option.charAt(0);
            } else {
                c = ' ';
            }

        } while (c < '1' || c > '2');

        switch (c) {
            case '1':
                gameModel.restartGame();
                break;
            case '2':
                quit = true;
                return;
        }

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
            } else if (state instanceof AwaitTrackSelection) {
                uiAwaitTrackSelection();
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
