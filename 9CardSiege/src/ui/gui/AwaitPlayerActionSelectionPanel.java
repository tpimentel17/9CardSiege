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
import logic.Constants;
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
    }

    private void setupComponents() {

        label = new JLabel("Action Points: " + observableGame.getGameData().getCurrentActionPoints());

        additionalActionPointsButton = new JButton("Additional Action Points");
        additionalActionPointsButton.addActionListener(new AdditionalActionPointsListener());
        //additionalActionPointsButton.setAlignmentX(CENTER_ALIGNMENT);

        archersAttackButton = new JButton("Archers Attack");
        archersAttackButton.addActionListener(new ArchersAttackListener());
        //archersAttackButton.setAlignmentX(CENTER_ALIGNMENT);

        boilingWaterAttackButton = new JButton("Boiling Water Attack");
        boilingWaterAttackButton.addActionListener(new BoilingWaterAttackListener());
        //boilingWaterAttackButton.setAlignmentX(CENTER_ALIGNMENT);

        closeCombatButton = new JButton("Close Combat");
        closeCombatButton.addActionListener(new CloseCombatListener());
        //closeCombatButton.setAlignmentX(CENTER_ALIGNMENT);

        coupureButton = new JButton("Coupure");
        coupureButton.addActionListener(new CoupureListener());
        //coupureButton.setAlignmentX(CENTER_ALIGNMENT);

        rallyTroopsButton = new JButton("Rally Troops");
        rallyTroopsButton.addActionListener(new RallyTroopsListener());
        //rallyTroopsButton.setAlignmentX(CENTER_ALIGNMENT);

        tunnelMovementButton = new JButton("Tunnel Movement");
        tunnelMovementButton.addActionListener(new TunnelMovementListener());
        //tunnelMovementButton.setAlignmentX(CENTER_ALIGNMENT);

        supplyRaidButton = new JButton("Supply Raid");
        supplyRaidButton.addActionListener(new SupplyRaidListener());
        //supplyRaidButton.setAlignmentX(CENTER_ALIGNMENT);

        sabotageButton = new JButton("Sabotage");
        sabotageButton.addActionListener(new SabotageListener());
        //sabotageButton.setAlignmentX(CENTER_ALIGNMENT);
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

        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());

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
