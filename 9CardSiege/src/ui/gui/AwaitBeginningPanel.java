package ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import logic.ObservableGame;
import logic.states.AwaitBegining;

public class AwaitBeginningPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    JButton startButton;

    public AwaitBeginningPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);

    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitBegining);
    }

    private void setupComponents() {
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.startGame();
            }
        });
    }

    private void setupLayout() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        panel.add(Box.createVerticalGlue());
        panel.add(startButton);
        panel.add(Box.createVerticalGlue());

        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }

}
