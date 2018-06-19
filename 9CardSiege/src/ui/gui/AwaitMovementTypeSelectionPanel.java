package ui.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static logic.Constants.*;
import logic.ObservableGame;
import logic.states.AwaitMovementTypeSelection;

public class AwaitMovementTypeSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;
    private JButton moveIntoTunnelButton;
    private JButton freeMovementButton;
    private JButton fastMovementButton;

    public AwaitMovementTypeSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        setBackground(Color.GRAY);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {

        setVisible(observableGame.getState() instanceof AwaitMovementTypeSelection);
        freeMovementButton.setEnabled(!observableGame.freeMovementWasUsed()
                && observableGame.getPlayerTracks().getSoldiersLocation() != CASTLE
                && observableGame.getPlayerTracks().getSoldiersLocation() != ENEMY_LINES);
        
        moveIntoTunnelButton.setEnabled(observableGame.getPlayerTracks().getSoldiersLocation() == CASTLE
                || observableGame.getPlayerTracks().getSoldiersLocation() == ENEMY_LINES);

    }

    private void setupComponents() {
        label = new JLabel("Select the type of movement to perform:");

        moveIntoTunnelButton = new JButton("Move Into the Tunnel");
        moveIntoTunnelButton.addActionListener((ActionEvent e) -> {
            observableGame.moveSoldiers(MOVE_INTO_TUNNEL);
        });

        freeMovementButton = new JButton("Free Movement");
        freeMovementButton.addActionListener((ActionEvent e) -> {
            observableGame.moveSoldiers(FREE_MOVEMENT);
        });

        fastMovementButton = new JButton("Fast Movement");
        fastMovementButton.addActionListener((ActionEvent e) -> {
            observableGame.moveSoldiers(FAST_MOVEMENT);
        });

    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(moveIntoTunnelButton);
        panel.add(Box.createVerticalGlue());
        panel.add(freeMovementButton);
        panel.add(Box.createVerticalGlue());
        panel.add(fastMovementButton);
        panel.add(Box.createVerticalGlue());

        panel.setBackground(Color.GRAY);

        add(panel);
    }
}
