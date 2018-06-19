package ui.gui;

import files.FileUtility;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import logic.Constants;
import logic.GameModel;
import logic.ObservableGame;
import logic.states.AwaitBegining;
import logic.states.AwaitTopCardToBeDrawn;
import logic.states.IStates;

public class NineCardSiegeFrame extends JFrame implements Constants, Observer {

    private ObservableGame observableGame;
    private JMenuItem load;
    private JMenuItem save;

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

        Box north = Box.createHorizontalBox();
        north.add(new DayPanel(observableGame));
        north.setBorder(new LineBorder(Color.BLACK));

        Box west = Box.createHorizontalBox();
        west.add(new DeckPanel(observableGame));
        west.setBorder(new LineBorder(Color.BLACK));
        
        Box center = Box.createHorizontalBox();
        center.add(new TracksPanel(observableGame));
        center.setBorder(new LineBorder(Color.BLACK));

        Box east = Box.createHorizontalBox();
        east.add(new ActionsPanel(observableGame));
        east.setBorder(new LineBorder(Color.BLACK));

        Box south = Box.createHorizontalBox();
        south.add(new AwaitBeginningPanel(observableGame));
        south.add(new AwaitGameFinishPanel(observableGame));
        south.setBorder(new LineBorder(Color.BLACK));

        cp.add(north, BorderLayout.NORTH);
        cp.add(east, BorderLayout.EAST);
        cp.add(center, BorderLayout.CENTER);
        cp.add(west, BorderLayout.WEST);
        cp.add(south, BorderLayout.SOUTH);
        cp.setBackground(Color.GRAY);
        

        //Panel
        pack();
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
        IStates state = observableGame.getState();
        load.setEnabled(state instanceof AwaitBegining);
        save.setEnabled(state instanceof AwaitTopCardToBeDrawn);
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // GameMenu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        load = new JMenuItem("Load");
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        load.setMnemonic(KeyEvent.VK_L);
        load.addActionListener(new LoadMenuItemListener());
        gameMenu.add(load);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(new SaveMenuItemListener());
        gameMenu.add(save);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.setMnemonic(KeyEvent.VK_E);
        exit.addActionListener(new ExitMenuItemListener());
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

    private class LoadMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showOpenDialog(NineCardSiegeFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    GameModel gameModel = (GameModel) FileUtility.loadGameFromFile(file);
                    if (gameModel != null) {
                        observableGame.setGameModel(gameModel);
                        JOptionPane.showMessageDialog(NineCardSiegeFrame.this, "Game loaded succesfully");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(NineCardSiegeFrame.this, "Operation failed: \r\n\r\n" + e);
                }

            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    private class SaveMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showSaveDialog(NineCardSiegeFrame.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    FileUtility.saveGameToFile(file, observableGame.getGameModel());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(NineCardSiegeFrame.this, "Operation failed: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operation canceled ");
            }
        }

    }

    private class ExitMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class AboutMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(NineCardSiegeFrame.this, "9 Card Siege\n\nTiago Pimentel\n21230519\n\n© 2018 ISEC \nAll Rights Reserved",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
