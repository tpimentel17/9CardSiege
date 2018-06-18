package ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static logic.Constants.*;
import logic.ObservableGame;
import logic.states.AwaitTrackSelection;

public class AwaitTrackSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;
    private JButton laddersButton;
    private JButton batteringRamButton;
    private JButton siegeTowerButton;

    public AwaitTrackSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitTrackSelection);
    }

    private void setupComponents() {
        label = new JLabel("Select the track to attack:");
       
        
        laddersButton = new JButton("Ladders");
        laddersButton.addActionListener((ActionEvent e) -> {
            observableGame.attackSelectedTrack(LADDERS);
        });
        
        batteringRamButton = new JButton("Battering Ram");
        batteringRamButton.addActionListener((ActionEvent e) -> {
            observableGame.attackSelectedTrack(BATTERING_RAM);
        });
        
        siegeTowerButton = new JButton("Siege Tower");
        siegeTowerButton.addActionListener((ActionEvent e) -> {
            observableGame.attackSelectedTrack(SIEGE_TOWER);
        });
        
    }

    private void setupLayout() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(laddersButton);
        panel.add(Box.createVerticalGlue());
        panel.add(batteringRamButton);
        panel.add(Box.createVerticalGlue());
        panel.add(siegeTowerButton);
        panel.add(Box.createVerticalGlue());
        
        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
        
    }
}
