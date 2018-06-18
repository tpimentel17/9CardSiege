package ui.txt;

import files.FileUtility;
import java.io.IOException;
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

    // <editor-fold desc="TEXT UI'S">
    private void uiAwaitBeginning() {
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
                try {
                    gameModel = (GameModel) FileUtility.loadGameFromFile();
                    System.out.println("Game was successfully loaded.");
                } catch (ClassNotFoundException ex) {
                    System.out.println("[EXCEPTION] Loaded object class not found!");
                } catch (IOException ex) {
                    System.out.println("[EXCEPTION] Problem loading object from file!");
                }
                
                break;
            case '3':
                quit = true;
                return;
        }
    }
    
    private void uiAwaitTopCardToBeDrawn() {
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
                FileUtility.saveGameToFile(gameModel);
                System.out.println("Game was successfully saved.");
                break;
            case '5':
                quit = true;
                return;
        }
    }
    
    private void uiAwaitPlayerActionSelection() {
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
            System.out.println("A - Aditional Action Points");
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
                option = option.toUpperCase();
                c = option.charAt(0);
            } else {
                c = ' ';
            }
            
        } while (c < '1' || c > '9' && c != 'A');
        
        switch (c) {
            case 'A':
                gameModel.additionalActionPointsSelected();
                break;
            case '1':
                gameModel.archersAttack();
                break;
            case '2':
                gameModel.boilingWaterAttack();
                break;
            case '3':
                gameModel.closeCombat();
                break;
            case '4':
                gameModel.coupure();
                break;
            case '5':
                gameModel.rallyTroopsOptions();
                break;
            case '6':
                gameModel.tunnelMovement();
                break;
            case '7':
                gameModel.supplyRaid();
                break;
            case '8':
                gameModel.sabotage();
                break;
            case '9':
                quit = true;
                return;
        }
    }
    
    private void uiAwaitTrackSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        
        if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(ARCHERS)) {
            System.out.println("\n=== SELECT THE TRACK TO BE ATTACKED BY THE ARCHERS - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        } else if (((AwaitTrackSelection) gameModel.getState()).getAction().equals(CLOSE_COMBAT)) {
            System.out.println("\n=== SELECT THE TRACK TO PERFORM THE CLOSE COMBAT - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
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
                gameModel.attackSelectedTrack(LADDERS);
                break;
            case '2':
                gameModel.attackSelectedTrack(BATTERING_RAM);
                break;
            case '3':
                gameModel.attackSelectedTrack(SIEGE_TOWER);
                break;
        }
    }
    
    private void uiAwaitGameFinish() {
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
            System.out.println("\n-------------> NÃO FOI DESTA QUE VENCEU... <-------------");
        }
        
        do {
            
            System.out.println();
            System.out.println("Do you want to start a new game?");
            System.out.println();
            System.out.println("1 - Yes");
            System.out.println("2 - No");
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
    
    private void uiAwaitOptionSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println("\n=== SELECT AN OPTION - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        
        do {
            
            System.out.println();
            System.out.println("You can spend 1 supplie to have a bigger chance of improving your people's Morale.");
            System.out.println("Do you wish to do it?");
            System.out.println();
            System.out.println("1 - Yes");
            System.out.println("2 - No");
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
                gameModel.rallyTroops(true);
                break;
            case '2':
                gameModel.rallyTroops(false);
                break;
        }
    }
    
    private void uiAwaitMovementTypeSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println();
        System.out.println("\n=== SELECT THE TYPE OF MOVEMENT TO PERFORM - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        
        do {
            
            System.out.println();
            System.out.println("1 - Move Into the Tunnel (1 Action Point)");
            System.out.println("2 - Free Movement");
            System.out.println("3 - Fast Movement (1 Action Point))");
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
                gameModel.moveSoldiers(MOVE_INTO_TUNNEL);
                break;
            case '2':
                gameModel.moveSoldiers(FREE_MOVEMENT);
                break;
            case '3':
                gameModel.moveSoldiers(FAST_MOVEMENT);
                break;
            case '4':
                quit = true;
                return;
            
        }
    }
    
    private void uiAwaitAdditionalActionPointsSelection() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************");
        
        System.out.println("\n=== SELECT WHICH POINT DO YOU WANT TO TRADE FOR AN ACTION POINT - DAY " + gameModel.getGameData().getCurrentDay() + " ===\n");
        
        do {
            System.out.println();
            System.out.println("1 - Morale Point");
            System.out.println("2 - Supply Point");
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
                gameModel.additionalActionPoint(MORALE);
                break;
            case '2':
                gameModel.additionalActionPoint(SUPPLY);
                break;
            
        }
    }

    //</editor-fold>
    public void run() {
        while (!quit) {

            //Apresenta mensagens do log
            if (!gameModel.getMessageLog().isEmpty()) {
                System.out.println();
                
                for (String msg : gameModel.getMessageLog()) {
                    System.out.println(msg);
                }
                
                gameModel.clearMessageLog();
            }
            
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
            } else if (state instanceof AwaitOptionSelection) {
                uiAwaitOptionSelection();
            } else if (state instanceof AwaitMovementTypeSelection) {
                uiAwaitMovementTypeSelection();
            } else if (state instanceof AwaitAdditionalActionPointsSelection) {
                uiAwaitAdditionalActionPointsSelection();
            }
        }
    }
    //--------------------------Private methods--------------------------

    private void showStatusTracks() {
        System.out.println();
        System.out.println("****Status Tracks****");
        System.out.println();
        System.out.println("Wall Strength -> " + gameModel.getGameData().getPlayerTracks().getWallStrength());
        System.out.println("Morale -> " + gameModel.getGameData().getPlayerTracks().getMorale());
        System.out.println("Supplies -> " + gameModel.getGameData().getPlayerTracks().getSupplies());
        System.out.println();
        System.out.println("Soldiers Position:");
        
        switch (gameModel.getGameData().getPlayerTracks().getSoldiersLocation()) {
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
        System.out.print("Raided Supplies -> " + gameModel.getGameData().getPlayerTracks().getNumberOfRaidedSupplies());
    }
    
    private void showEnemyTracks() {
        
        int laddersPosition = gameModel.getGameData().getEnemyTracks().getLaddersPosition();
        int ramPosition = gameModel.getGameData().getEnemyTracks().getRamPosition();
        int towerPosition = gameModel.getGameData().getEnemyTracks().getTowerPosition();
        
        System.out.println();
        System.out.println("****Enemy Tracks****");
        System.out.println();
        System.out.println("Close combat units -> " + gameModel.getGameData().getEnemyTracks().getNumberOfUnitsInCloseCombat() + "\t\tStrength: 4 (each)");
        System.out.println("Ladders distance -> " + laddersPosition + "\t\tStrength: 2");
        System.out.println("Battering Ram distance -> " + ramPosition + "\tStrength: 3");
        System.out.println("Siege Tower distance -> " + towerPosition + "\tStrength: 4");
        System.out.println("Number of Trebuchet(s) -> " + gameModel.getGameData().getEnemyTracks().getNumberOfTrebuchets());
    }
    
}
