package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static logic.Constants.*;
import logic.ObservableGame;
import logic.states.*;

public class LogPanel extends JPanel implements Observer {
    
    private ObservableGame observableGame;
    
    private JTextArea gameLog;
    private JScrollPane logScroll;
    
    public LogPanel(ObservableGame observableGame) {
        
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        
        setupComponents();
        setupLayout();
        
        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, LOG_PANEL_HEIGHT));
        
        update(observableGame, null);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        gameLog.setText(observableGame.getGameData().getMessageLog() + "\n");
        
        setVisible(!(observableGame.getState() instanceof AwaitBegining) && !(observableGame.getState() instanceof AwaitGameFinish));
    }
    
    private void setupComponents() {
        gameLog = new JTextArea(FRAME_WIDTH - BORDER_X_GAP, LOG_PANEL_HEIGHT - BORDER_X_GAP / 2);
        gameLog.setFont(new Font("Arial", PLAIN, 14));
        gameLog.setBackground(Color.LIGHT_GRAY);
        logScroll = new JScrollPane(gameLog);
        
        logScroll.setMaximumSize(new Dimension(FRAME_WIDTH - 2 * BORDER_X_GAP, LOG_PANEL_HEIGHT - BORDER_X_GAP));
        
    }
    
    private void setupLayout() {
        
        Box box = Box.createVerticalBox();
        box.add(Box.createHorizontalGlue());
        box.add(logScroll);
        box.add(Box.createHorizontalGlue());
        
        add(box);
        
    }
    
}
