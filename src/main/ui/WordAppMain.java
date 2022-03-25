package ui;

import model.WordGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

/*
 * Represents the main window in which the space invaders
 * game is played
 */

// SOURCE: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
public class WordAppMain extends JFrame {
    private static final String JSON_STORE = "./data/history.json";
    private static final int INTERVAL = 20;
    private WordGame game;
    private GamePanel gp;
    private StatsPanel sp;
    private KeyboardPanel kp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public WordAppMain() throws FileNotFoundException {
        super("Speed Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        game = new WordGame();
        gp = new GamePanel(game);
        sp = new StatsPanel(game);
        kp = new KeyboardPanel();
        add(gp);
        add(sp, BorderLayout.NORTH);
        add(kp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> {
            gp.remove(0);
            game.update();
            gp.revalidate();
            gp.repaint();
            sp.update();
        });

        t.start();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_R && game.isOver()) {
                game.restartGame();
            } else {
                game.keyPressed(e.getKeyCode());
            }
        }
    }

    // EFFECTS: saves the workroom to file
//    private void saveGame() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(guesses);
//            jsonWriter.close();
//            System.out.println("Saved current game to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
//    private void loadGame() {
//        try {
//            guesses = jsonReader.read();
//            timer = jsonReader.readTime();
//            System.out.println("Loaded old game from " + JSON_STORE);
//            gameActive = true;
//            getWordInputs();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }

    /*
     * Play the game
     */
    public static void main(String[] args) throws FileNotFoundException {
        new WordAppMain();
    }
}
