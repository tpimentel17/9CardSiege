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
import logic.states.AwaitTopCardToBeDrawn;

public class AwaitTopCardToBeDrawnPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JButton drawCard;

    public AwaitTopCardToBeDrawnPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitTopCardToBeDrawn);
    }

    private void setupComponents() {
        drawCard = new JButton("Draw Card");
        drawCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.drawTopCard();
            }
        });
    }

    private void setupLayout() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        panel.add(Box.createVerticalGlue());
        panel.add(drawCard);
        panel.add(Box.createVerticalGlue());
        
        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }

}
