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
        TunnelToken(g);
        RaidedSuppliesToken(g);

        TrebuchetToken(g);
        LadderToken(g);
        RamToken(g);
        TowerToken(g);
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

    private void TunnelToken(Graphics g) {

        g.setColor(Color.YELLOW);

        switch (observableGame.getPlayerTracks().getSoldiersLocation()) {
            case CASTLE:
                drawCircle(g, PLAYER_TUNNEL_COL_ANCHOR + TUNNEL_COL_DISPLACEMENT * 0, PLAYER_TUNNEL_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case TUNNEL_CASTLE:
                drawCircle(g, PLAYER_TUNNEL_COL_ANCHOR + TUNNEL_COL_DISPLACEMENT * 1, PLAYER_TUNNEL_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case TUNNEL_ENEMY:
                drawCircle(g, PLAYER_TUNNEL_COL_ANCHOR + TUNNEL_COL_DISPLACEMENT * 2, PLAYER_TUNNEL_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case ENEMY_LINES:
                drawCircle(g, PLAYER_TUNNEL_COL_ANCHOR + TUNNEL_COL_DISPLACEMENT * 3 + 5, PLAYER_TUNNEL_ROW_ANCHOR, TOKEN_RADIUS);
                break;
        }
    }

    private void RaidedSuppliesToken(Graphics g) {

        g.setColor(Color.YELLOW);

        switch (observableGame.getPlayerTracks().getNumberOfRaidedSupplies()) {
            case 1:
                drawCircle(g, RAIDED_SUPPLIES_COL, RAIDED_SUPPLIES_ROW, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, RAIDED_SUPPLIES_COL, RAIDED_SUPPLIES_ROW - RAIDED_SUPPLIES_ROW_DISPLACEMENT, TOKEN_RADIUS);
                break;
        }
    }

    private void TrebuchetToken(Graphics g) {
        g.setColor(Color.YELLOW);

        switch (observableGame.getEnemyTracks().getNumberOfTrebuchets()) {
            case 1:
                drawCircle(g, TREBUCHET_COL_ANCHOR, TREBUCHET_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, TREBUCHET_COL_ANCHOR + TREBUCHET_COL_DISPLACEMENT, TREBUCHET_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, TREBUCHET_COL_ANCHOR + TREBUCHET_COL_DISPLACEMENT * 2, TREBUCHET_ROW_ANCHOR, TOKEN_RADIUS);
                break;
        }
    }

    private void LadderToken(Graphics g) {
        g.setColor(Color.red);

        switch (observableGame.getEnemyTracks().getLaddersPosition()) {
            case 1:
                drawCircle(g, ENEMY_COL_ANCHOR, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, ENEMY_COL_ANCHOR, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, ENEMY_COL_ANCHOR, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 4:
                drawCircle(g, ENEMY_COL_ANCHOR, ENEMY_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT - CLOSE_COMBAT_COL_DISPLACEMENT,
                        ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3 - CLOSE_COMBAT_ROW_DISPLACEMENT, TOKEN_RADIUS);

                break;
        }
    }

    private void RamToken(Graphics g) {
        g.setColor(Color.green);

        switch (observableGame.getEnemyTracks().getRamPosition()) {
            case 1:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 4:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT, ENEMY_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT,
                        ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3 - CLOSE_COMBAT_ROW_DISPLACEMENT, TOKEN_RADIUS);

                break;
        }
    }

    private void TowerToken(Graphics g) {
        g.setColor(Color.blue);

        switch (observableGame.getEnemyTracks().getTowerPosition()) {
            case 1:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT * 2, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3, TOKEN_RADIUS);
                break;
            case 2:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT * 2, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 2, TOKEN_RADIUS);
                break;
            case 3:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT * 2, ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 1, TOKEN_RADIUS);
                break;
            case 4:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT * 2, ENEMY_ROW_ANCHOR, TOKEN_RADIUS);
                break;
            case 0:
                drawCircle(g, ENEMY_COL_ANCHOR + ENEMY_COL_DISPLACEMENT + CLOSE_COMBAT_COL_DISPLACEMENT,
                        ENEMY_ROW_ANCHOR - CARD_ROW_DISPLACEMENT * 3 - CLOSE_COMBAT_ROW_DISPLACEMENT, TOKEN_RADIUS);
                break;
        }
    }
}
