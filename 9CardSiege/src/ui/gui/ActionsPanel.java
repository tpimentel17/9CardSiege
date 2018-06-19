package ui.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JPanel;
import logic.Constants;
import static logic.Constants.*;
import logic.ObservableGame;

public class ActionsPanel extends JPanel {

    //private ObservableGame observableGame;

    public ActionsPanel(ObservableGame observableGame) {

        //this.observableGame = observableGame;
        //this.observableGame.addObserver(this);

        add(Box.createHorizontalGlue());
        add(new AwaitAdditionalActionPointsSelectionPanel(observableGame));
        add(new AwaitMovementTypeSelectionPanel(observableGame));
        add(new AwaitOptionSelectionPanel(observableGame));
        add(new AwaitPlayerActionSelectionPanel(observableGame));
        add(new AwaitTopCardToBeDrawnPanel(observableGame));
        add(new AwaitTrackSelectionPanel(observableGame));
        add(Box.createHorizontalGlue());

        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(ACTIONS_WIDTH, CENTER_PANEL_HEIGHT));
        setPreferredSize(new Dimension(ACTIONS_WIDTH, CENTER_PANEL_HEIGHT));
        setMinimumSize(new Dimension(ACTIONS_WIDTH, CENTER_PANEL_HEIGHT));

    }

}
