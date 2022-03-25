package ui;

import model.WordGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {
    private static final String TIME_TAKEN_TXT = "Time: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private WordGame game;
    private JLabel timeTakenLbl;
    private HelpButton helpBtn;
    private boolean helpButtonOn;

    // Constructs a stats panel for the word game
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose timer is to be displayed
    public StatsPanel(WordGame g, HelpButton btn) {
        game = g;
        setBackground(new Color(180, 180, 180));
        timeTakenLbl = new JLabel(TIME_TAKEN_TXT + game.getTimeElapsed());
        timeTakenLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(timeTakenLbl);
        helpBtn = btn;
//        add(helpBtn);
        helpButtonOn = false;
        helpBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Image img = new ImageIcon("./data/instructions.png").getImage();
                Image newImg = img.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newImg);
                helpButtonOn = !helpButtonOn;
                if (helpButtonOn) {
                    helpBtn.setIcon(icon);
                } else {
                    helpBtn.setIcon(null);
                }
            }
        });
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates timer of current game in stats panel
    public void update() {
        timeTakenLbl.setText(TIME_TAKEN_TXT + game.getTimeElapsed());
        repaint();
    }
}
