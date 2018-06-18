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
import logic.states.AwaitAdditionalActionPointsSelection;

public class AwaitAdditionalActionPointsSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;
    private JButton moraleButton;
    private JButton supplyButton;

    public AwaitAdditionalActionPointsSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitAdditionalActionPointsSelection);
        moraleButton.setEnabled(observableGame.getPlayerTracks().getMorale() > 0);
        supplyButton.setEnabled(observableGame.getPlayerTracks().getSupplies() > 0);
    }

    private void setupComponents() {
        label = new JLabel("Select which point do you want to trade for an Action Point:");
        
        moraleButton = new JButton("Morale");
        moraleButton.addActionListener((ActionEvent e) -> {
            observableGame.additionalActionPoint(MORALE);
        });
        
        supplyButton = new JButton("Supply");
        supplyButton.addActionListener((ActionEvent e) -> {
            observableGame.additionalActionPoint(SUPPLY);
        });

    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(moraleButton);
        panel.add(Box.createVerticalGlue());
        panel.add(supplyButton);
        panel.add(Box.createVerticalGlue());

        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }
}
