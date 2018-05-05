package ui.txt;

import java.util.Scanner;
import logic.GameModel;
import logic.states.AwaitBegining;
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
                break;
            case '2':
                break;
            case '3':
                quit=true;
                return;
        }
    }
    
    public void run(){
        while(!quit){
            IStates state = gameModel.getState();
            
            if(state instanceof AwaitBegining){
                uiAwaitBeginning();
            }
                
        }
    }
}
