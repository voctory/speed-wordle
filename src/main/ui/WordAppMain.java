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


//
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
    private HelpButton hlpBtn;

    // MODIFIES: this (JFrame)
    // EFFECTS: sets up window in which Speed Wordle will be played
    public WordAppMain() throws FileNotFoundException {
        super("Speed Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        game = new WordGame();
        gp = new GamePanel(game);
        hlpBtn = new HelpButton();
        sp = new StatsPanel(game, hlpBtn);
        kp = new KeyboardPanel();
        add(hlpBtn, BorderLayout.EAST);
        hlpBtn.setFocusable(false);
        add(gp);
        add(sp, BorderLayout.NORTH);
        add(kp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE, game);
    }

    // EFFECTS:  initializes a timer that updates game each
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
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: handles key presses
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_R && game.isOver()) {
                game.restartGame();
            } else if (e.getKeyCode() == KeyEvent.VK_SHIFT && !game.isOver()) {
                saveGame();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !game.isOver()) {
                loadGame();
            } else {
                game.keyPressed(e.getKeyCode());
            }
        }
    }

    // EFFECTS: saves the word game state to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game.toJson());
            jsonWriter.close();
            System.out.println("Saved current game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads word game state from file
    private void loadGame() {
        try {
            jsonReader.read();
            System.out.println("Loaded old game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: starts the game
    public static void main(String[] args) throws FileNotFoundException {
        new WordAppMain();
    }
}
