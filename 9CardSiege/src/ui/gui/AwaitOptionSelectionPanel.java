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
import logic.ObservableGame;
import logic.states.AwaitOptionSelection;

public class AwaitOptionSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label1, label2, label3;
    private JButton yesButton;
    private JButton noButton;

    public AwaitOptionSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        setBackground(Color.GRAY);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitOptionSelection);

    }

    private void setupComponents() {

        label1 = new JLabel("You can spend 1 supply to have a bigger");
        label2 = new JLabel("chance of improving your people's Morale.");
        label3 = new JLabel("Do you wish to do it?");

        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.rallyTroops(true);
            }
        });

        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.rallyTroops(false);
            }
        });

    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        panel.add(Box.createVerticalGlue());
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(Box.createVerticalGlue());
        panel.add(yesButton);
        panel.add(Box.createVerticalGlue());
        panel.add(noButton);
        panel.add(Box.createVerticalGlue());

        panel.setBackground(Color.GRAY);

        add(panel);
    }

}
