package ui.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import logic.Constants;
import logic.ObservableGame;

public class NineCardSiegeFrame extends JFrame implements Constants, Observer {

    private ObservableGame observableGame;

    public NineCardSiegeFrame(ObservableGame observableGame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        // Centra a janela
        int frameX = (int) ((width - FRAME_WIDTH) / 2);
        int frameY = (int) ((height - FRAME_HEIGHT) / 2);

        new NineCardSiegeFrame(observableGame, frameX, frameY, FRAME_WIDTH, FRAME_HEIGHT);
    }

    public NineCardSiegeFrame(ObservableGame observableGame, int x, int y, int width, int height) {
        super("9 Card Siege");

        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        Container cp = getContentPane();
        menu();

        //Panel
        setLocation(x, y);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // GameMenu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        JMenuItem load = new JMenuItem("Load");
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        load.setMnemonic(KeyEvent.VK_L);
        gameMenu.add(load);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.setMnemonic(KeyEvent.VK_S);
        gameMenu.add(save);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.setMnemonic(KeyEvent.VK_E);
        gameMenu.add(exit);

        menuBar.add(gameMenu);

        //HelpMenu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        
        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(new AboutMenuItemListener());
        helpMenu.add(about);
        
        menuBar.add(helpMenu);
    }
    
    private class AboutMenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                  JOptionPane.showMessageDialog(NineCardSiegeFrame.this, "9 Card Siege\n\nTiago Pimentel\n21230519\n\n© 2018 ISEC \nAll Rights Reserved",
                          "About", JOptionPane.INFORMATION_MESSAGE);}
        
    }
}
