package ui.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import logic.ObservableGame;

public class Images extends JPanel implements Observer {

    static private BufferedImage card1Image = null;
    static private BufferedImage card2Image = null;
    static private BufferedImage card3Image = null;
    static private BufferedImage card4Image = null;
    static private BufferedImage card5Image = null;
    static private BufferedImage card6Image = null;
    static private BufferedImage card7Image = null;

    static private BufferedImage dice1Image = null;
    static private BufferedImage dice2Image = null;
    static private BufferedImage dice3Image = null;
    static private BufferedImage dice4Image = null;
    static private BufferedImage dice5Image = null;
    static private BufferedImage dice6Image = null;

    static private BufferedImage enemyTracksImage = null;
    static private BufferedImage playerTracksImage = null;

    static {
        try {
            card1Image = ImageIO.read(Resources.getResourceFile("images/card1.png"));
            card2Image = ImageIO.read(Resources.getResourceFile("images/card2.png"));
            card3Image = ImageIO.read(Resources.getResourceFile("images/card3.png"));
            card4Image = ImageIO.read(Resources.getResourceFile("images/card4.png"));
            card5Image = ImageIO.read(Resources.getResourceFile("images/card5.png"));
            card6Image = ImageIO.read(Resources.getResourceFile("images/card6.png"));
            card7Image = ImageIO.read(Resources.getResourceFile("images/card7.png"));

            dice1Image = ImageIO.read(Resources.getResourceFile("images/dice1.png"));
            dice2Image = ImageIO.read(Resources.getResourceFile("images/dice2.png"));
            dice3Image = ImageIO.read(Resources.getResourceFile("images/dice3.png"));
            dice4Image = ImageIO.read(Resources.getResourceFile("images/dice4.png"));
            dice5Image = ImageIO.read(Resources.getResourceFile("images/dice5.png"));
            dice6Image = ImageIO.read(Resources.getResourceFile("images/dice6.png"));

            enemyTracksImage = ImageIO.read(Resources.getResourceFile("images/enemytracks.png"));
            playerTracksImage = ImageIO.read(Resources.getResourceFile("images/playertracks.png"));
        } catch (IOException e) {
            System.out.println("Error loading images ");
        }
    }

    public static BufferedImage getEnemyTracksImage() {
        return enemyTracksImage;
    }

    public static BufferedImage getPlayerTracksImage() {
        return playerTracksImage;
    }

    public static BufferedImage getCard1Image() {
        return card1Image;
    }

    public static BufferedImage getCard2Image() {
        return card2Image;
    }

    public static BufferedImage getCard3Image() {
        return card3Image;
    }

    public static BufferedImage getCard4Image() {
        return card4Image;
    }

    public static BufferedImage getCard5Image() {
        return card5Image;
    }

    public static BufferedImage getCard6Image() {
        return card6Image;
    }
    
    public static BufferedImage getCard7Image() {
        return card7Image;
    }
    
    public static BufferedImage getDice1Image() {
        return dice1Image;
    }

    public static BufferedImage getDice2Image() {
        return dice2Image;
    }

    public static BufferedImage getDice3Image() {
        return dice3Image;
    }

    public static BufferedImage getDice4Image() {
        return dice4Image;
    }

    public static BufferedImage getDice5Image() {
        return dice5Image;
    }

    public static BufferedImage getDice6Image() {
        return dice6Image;
    }
    

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
