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
import static logic.Constants.VICTORY;
import logic.ObservableGame;
import logic.states.AwaitGameFinish;

public class AwaitGameFinishPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label1;
    private JLabel label2;
    private JButton yesButton;
    private JButton noButton;

    public AwaitGameFinishPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        setBackground(Color.GRAY);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitGameFinish);
    }

    private void setupComponents() {
        label1 = new JLabel();
        if (observableGame.getState() instanceof AwaitGameFinish) {
            if (((AwaitGameFinish) observableGame.getState()).getResult() == VICTORY) {
                label1 = new JLabel("Congratulations! You have survived the siege!");
            } else {
                label1 = new JLabel("You've been defeated by your enemey!");
            }
        }

        label2 = new JLabel("Do you want to start a new game?");

        yesButton = new JButton("Yes");
        yesButton.addActionListener((ActionEvent e) -> {
            observableGame.restartGame();
        });

        noButton = new JButton("No");
        noButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.GRAY);

        Box box = Box.createHorizontalBox();

        box.add(Box.createHorizontalGlue());
        box.add(yesButton);
        box.add(Box.createHorizontalGlue());
        box.add(noButton);
        box.add(Box.createHorizontalGlue());

        panel.add(Box.createVerticalGlue());
        panel.add(label1);
        panel.add(Box.createVerticalGlue());
        panel.add(label2);
        panel.add(Box.createVerticalGlue());
        panel.add(box);
        panel.add(Box.createVerticalGlue());

        add(panel);

    }
}
