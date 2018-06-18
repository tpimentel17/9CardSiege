package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import static logic.Constants.*;
import logic.ObservableGame;

public class TracksPanel extends JPanel implements Observer {

    private ObservableGame observableGame;

    public TracksPanel(ObservableGame observableGame) {

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(TRACK_WIDTH, CENTER_PANEL_HEIGHT));
        setPreferredSize(new Dimension(TRACK_WIDTH, CENTER_PANEL_HEIGHT));
        setMinimumSize(new Dimension(TRACK_WIDTH, CENTER_PANEL_HEIGHT));

    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Images.getPlayerTracksImage(), BORDER_X_GAP, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);
        g.drawImage(Images.getEnemyTracksImage(), BORDER_X_GAP * 2 + CARD_WIDTH, BORDER_Y_GAP, CARD_WIDTH, CARD_HEIGHT, this);

        //TOKENS
        WallToken(g);
        MoraleToken(g);
        SuppliesToken(g);

    }

    private void drawCircle(Graphics g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval(x, y, r, r);
    }

    private void WallToken(Graphics g) {
        g.setColor(Color.red);
        switch (observableGame.getPlayerTracks().getWallStrength()) {
            case 4:
                drawCircle(g, PLAYER_COL_ANCHOR, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 0, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, PLAYER_COL_ANCHOR, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, PLAYER_COL_ANCHOR, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 1:
                drawCircle(g, PLAYER_COL_ANCHOR, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 4, TOKEN_RADIUS);
                break;
        }
    }

    private void MoraleToken(Graphics g) {
        g.setColor(Color.green);
        switch (observableGame.getPlayerTracks().getMorale()) {
            case 4:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 0, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 1:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 4, TOKEN_RADIUS);
                break;
        }
    }

    private void SuppliesToken(Graphics g) {
        g.setColor(Color.blue);
        switch (observableGame.getPlayerTracks().getSupplies()) {
            case 4:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT * 2, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 0, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT * 2, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT * 2, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 1:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT * 2, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, PLAYER_COL_ANCHOR + CARD_COL_DISPLACEMENT, PLAYER_ROW_ANCHOR + CARD_ROW_DISPLACEMENT * 4, TOKEN_RADIUS);
                break;
        }
    }
}
