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
import logic.ObservableGame;
import logic.states.AwaitOptionSelection;

public class AwaitOptionSelectionPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;
    private JButton yesButton;
    private JButton noButton;

    public AwaitOptionSelectionPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitOptionSelection);
    }

    private void setupComponents() {

        label = new JLabel("You can spend 1 supplie to have a bigger chance of improving your people's Morale. "
                + "\nDo you wish to do it?");

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
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(yesButton);
        panel.add(Box.createVerticalGlue());
        panel.add(noButton);
        panel.add(Box.createVerticalGlue());
        
        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }

}
