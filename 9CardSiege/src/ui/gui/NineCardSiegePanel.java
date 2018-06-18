package ui.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import logic.ObservableGame;

public class NineCardSiegePanel extends JPanel implements Observer {

    // <editor-fold desc="IMAGES">
    static private BufferedImage card1 = null;
    static private BufferedImage card2 = null;
    static private BufferedImage card3 = null;
    static private BufferedImage card4 = null;
    static private BufferedImage card5 = null;
    static private BufferedImage card6 = null;
    static private BufferedImage card7 = null;

    static private BufferedImage dice1 = null;
    static private BufferedImage dice2 = null;
    static private BufferedImage dice3 = null;
    static private BufferedImage dice4 = null;
    static private BufferedImage dice5 = null;
    static private BufferedImage dice6 = null;

    static private BufferedImage enemyTracks = null;
    static private BufferedImage playerTracks = null;

    static {
        try {
            card1 = ImageIO.read(Resources.getResourceFile("images/card1.png"));
            card2 = ImageIO.read(Resources.getResourceFile("images/card2.png"));
            card3 = ImageIO.read(Resources.getResourceFile("images/card3.png"));
            card4 = ImageIO.read(Resources.getResourceFile("images/card4.png"));
            card5 = ImageIO.read(Resources.getResourceFile("images/card5.png"));
            card6 = ImageIO.read(Resources.getResourceFile("images/card6.png"));
            card7 = ImageIO.read(Resources.getResourceFile("images/card7.png"));

            dice1 = ImageIO.read(Resources.getResourceFile("images/dice1.png"));
            dice2 = ImageIO.read(Resources.getResourceFile("images/dice2.png"));
            dice3 = ImageIO.read(Resources.getResourceFile("images/dice3.png"));
            dice4 = ImageIO.read(Resources.getResourceFile("images/dice4.png"));
            dice5 = ImageIO.read(Resources.getResourceFile("images/dice5.png"));
            dice6 = ImageIO.read(Resources.getResourceFile("images/dice6.png"));

            enemyTracks = ImageIO.read(Resources.getResourceFile("images/enemytracks.png"));
            playerTracks = ImageIO.read(Resources.getResourceFile("images/playertracks.png"));
        } catch (IOException e) {
            System.out.println("Error loading images ");
        }
    }
    // </editor-fold>
    
    private ObservableGame observableGame;

    public NineCardSiegePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        
        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
    
    
}
