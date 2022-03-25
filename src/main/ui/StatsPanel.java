package ui;

import model.WordGame;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private static final String TIME_TAKEN_TXT = "Time taken: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private WordGame game;
    private JLabel timeTakenLbl;

    // Constructs a stats panel for the word game
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose timer is to be displayed
    public StatsPanel(WordGame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        timeTakenLbl = new JLabel(TIME_TAKEN_TXT + game.getTimeElapsed());
        timeTakenLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(timeTakenLbl);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates timer of current game in stats panel
    public void update() {
        timeTakenLbl.setText(TIME_TAKEN_TXT + game.getTimeElapsed());
        repaint();
    }
}
