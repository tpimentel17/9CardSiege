package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static logic.Constants.*;
import logic.ObservableGame;
import logic.cards.*;

public class DeckPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    private JLabel label;

    public DeckPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setupComponents();
        setupLayout();

        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(DECK_WIDTH, CENTER_PANEL_HEIGHT));
        setPreferredSize(new Dimension(DECK_WIDTH, CENTER_PANEL_HEIGHT));
        setMinimumSize(new Dimension(DECK_WIDTH, CENTER_PANEL_HEIGHT));
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        setVisible(true);
        label.setText("Remaining Cards: " + observableGame.getGameData().getDeck().size());

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCard(g);
        drawDice(g);

    }

    private void drawCard(Graphics g) {
        if (!observableGame.getGameData().getDrawnCards().isEmpty()) {
            ArrayList drawnCards = observableGame.getGameData().getDrawnCards();
            Card card = (Card) drawnCards.get(drawnCards.size() - 1);

            if (card instanceof Card1) {
                g.drawImage(Images.getCard1Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card2) {
                g.drawImage(Images.getCard2Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card3) {
                g.drawImage(Images.getCard3Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card4) {
                g.drawImage(Images.getCard4Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card5) {
                g.drawImage(Images.getCard5Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card6) {
                g.drawImage(Images.getCard6Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            } else if (card instanceof Card7) {
                g.drawImage(Images.getCard7Image(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

            }
        } else {
            g.drawImage(Images.getCardBackImage(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

        }
    }

    private void drawDice(Graphics g) {

        switch (observableGame.getGameData().getDie().getValue()) {
            case 1:
                g.drawImage(Images.getDice1Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
            case 2:
                g.drawImage(Images.getDice2Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
            case 3:
                g.drawImage(Images.getDice3Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
            case 4:
                g.drawImage(Images.getDice4Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
            case 5:
                g.drawImage(Images.getDice5Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
            case 6:
                g.drawImage(Images.getDice6Image(), (getSize().width - DICE_SIZE) / 2, BORDER_Y_GAP * 2 + CARD_HEIGHT, DICE_SIZE, DICE_SIZE, this);
                break;
        }
    }

    private void setupComponents() {
        label = new JLabel("Remaining Cards: " + observableGame.getGameData().getDeck().size());

    }

    private void setupLayout() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.GRAY);

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());

        add(Box.createHorizontalGlue());
        add(panel);
        add(Box.createHorizontalGlue());
    }

}
