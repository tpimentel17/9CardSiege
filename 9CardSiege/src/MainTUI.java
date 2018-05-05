
import logic.GameModel;
import ui.txt.TextUI;

//TEXT UI MAIN
public class MainTUI {
 
    public static void main(String[] args){
        TextUI textUI = new TextUI(new GameModel());
        textUI.run();
    }
}
