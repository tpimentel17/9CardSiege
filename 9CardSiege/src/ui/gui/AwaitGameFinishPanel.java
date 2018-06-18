package ui.gui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static logic.Constants.VICTORY;
import logic.ObservableGame;

public class AwaitGameFinishPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;
    private JButton yesButton;
    private JButton noButton;

    public AwaitGameFinishPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    private void setupComponents() {

        
        
        
    
    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(1, 0));
    }
}
