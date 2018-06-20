package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static logic.Constants.*;
import logic.ObservableGame;

public class SouthPanel extends JPanel implements Observer {

    private ObservableGame observableGame;
    

    public SouthPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupLayout();

        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(true);
    }
    
    private void setupLayout() {
        add(new AwaitBeginingPanel(observableGame));
        add(new AwaitGameFinishPanel(observableGame));
        add(new LogPanel(observableGame));
    }

}
