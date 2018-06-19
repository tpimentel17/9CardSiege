package ui.gui;

import java.awt.Color;
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
import logic.states.AwaitPlayerActionSelection;

public class AwaitPlayerActionSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;

    private JButton additionalActionPointsButton;
    private JButton archersAttackButton;
    private JButton boilingWaterAttackButton;
    private JButton closeCombatButton;
    private JButton coupureButton;
    private JButton rallyTroopsButton;
    private JButton tunnelMovementButton;
    private JButton supplyRaidButton;
    private JButton sabotageButton;

    public AwaitPlayerActionSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        setBackground(Color.GRAY);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitPlayerActionSelection);
        additionalActionPointsButton.setEnabled(!observableGame.additionalActionalActionPointWasUsed());
        boilingWaterAttackButton.setEnabled(!observableGame.boiledWaterWasUsed() && observableGame.getEnemyTracks().getNumberOfUnitsInCircleSpaces() > 0);
        closeCombatButton.setEnabled(observableGame.getEnemyTracks().getNumberOfUnitsInCloseCombat() > 0);
        supplyRaidButton.setEnabled(observableGame.getPlayerTracks().getSoldiersLocation() == ENEMY_LINES);
        sabotageButton.setEnabled(observableGame.getPlayerTracks().getSoldiersLocation() == ENEMY_LINES);
        label.setText("Action Points: " + observableGame.getGameData().getCurrentActionPoints());

        if (observableGame.getEnemyTracks().getNumberOfUnitsInCloseCombat() == 2) {
            archersAttackButton.setEnabled(false);
            boilingWaterAttackButton.setEnabled(false);
            coupureButton.setEnabled(false);
            rallyTroopsButton.setEnabled(false);
            tunnelMovementButton.setEnabled(false);
            supplyRaidButton.setEnabled(false);
            sabotageButton.setEnabled(false);
        }

    }

    private void setupComponents() {

        label = new JLabel("Action Points: " + observableGame.getGameData().getCurrentActionPoints());

        additionalActionPointsButton = new JButton("Additional Action Points");
        additionalActionPointsButton.addActionListener(new AdditionalActionPointsListener());

        archersAttackButton = new JButton("Archers Attack");
        archersAttackButton.addActionListener(new ArchersAttackListener());

        boilingWaterAttackButton = new JButton("Boiling Water Attack");
        boilingWaterAttackButton.addActionListener(new BoilingWaterAttackListener());

        closeCombatButton = new JButton("Close Combat");
        closeCombatButton.addActionListener(new CloseCombatListener());

        coupureButton = new JButton("Coupure");
        coupureButton.addActionListener(new CoupureListener());

        rallyTroopsButton = new JButton("Rally Troops");
        rallyTroopsButton.addActionListener(new RallyTroopsListener());

        tunnelMovementButton = new JButton("Tunnel Movement");
        tunnelMovementButton.addActionListener(new TunnelMovementListener());

        supplyRaidButton = new JButton("Supply Raid");
        supplyRaidButton.addActionListener(new SupplyRaidListener());

        sabotageButton = new JButton("Sabotage");
        sabotageButton.addActionListener(new SabotageListener());

    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(additionalActionPointsButton);
        panel.add(Box.createVerticalGlue());
        panel.add(archersAttackButton);
        panel.add(Box.createVerticalGlue());
        panel.add(boilingWaterAttackButton);
        panel.add(Box.createVerticalGlue());
        panel.add(closeCombatButton);
        panel.add(Box.createVerticalGlue());
        panel.add(coupureButton);
        panel.add(Box.createVerticalGlue());
        panel.add(rallyTroopsButton);
        panel.add(Box.createVerticalGlue());
        panel.add(tunnelMovementButton);
        panel.add(Box.createVerticalGlue());
        panel.add(supplyRaidButton);
        panel.add(Box.createVerticalGlue());
        panel.add(sabotageButton);
        panel.add(Box.createVerticalGlue());

        panel.setBackground(Color.GRAY);

        add(panel);

    }

    private class AdditionalActionPointsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.additionalActionPointsSelected();
        }

    }

    private class ArchersAttackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.archersAttack();
        }

    }

    private class BoilingWaterAttackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.boilingWaterAttack();
        }

    }

    private class CloseCombatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.closeCombat();
        }

    }

    private class CoupureListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.coupure();
        }

    }

    private class RallyTroopsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.rallyTroopsOptions();
        }

    }

    private class TunnelMovementListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.tunnelMovement();
        }

    }

    private class SupplyRaidListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.supplyRaid();
        }

    }

    private class SabotageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.sabotage();
        }

    }

}
