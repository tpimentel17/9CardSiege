package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.ObservableGame;

public class DayPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;

    public DayPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();
        
        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        label.setText("9 CARD SIEGE - DAY " + observableGame.getGameData().getCurrentDay());
    }

    private void setupComponents() {
        label = new JLabel("9 CARD SIEGE - DAY " + observableGame.getGameData().getCurrentDay());
        label.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.GRAY);

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        
        setBackground(Color.GRAY);
        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }
}
