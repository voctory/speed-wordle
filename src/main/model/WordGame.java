package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class WordGame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private boolean isGameOver;

    private WordHistory wordHistory;
    private Word currentGuess;
    private Guessing actualWord;
    private SolveTimer solveTimer;
    private String timeElapsedCache;

    // Constructs a Speed Wordle Game
    // effects:  creates empty lists of missiles and invaders, centres tank on screen
    public WordGame() {
        wordHistory = new WordHistory();
        actualWord = new Guessing(wordHistory);
        // use an empty string as the current guess
        currentGuess = new Word("", actualWord, wordHistory);
        solveTimer = new SolveTimer();
        timeElapsedCache = "";
    }

    // Updates the game on clock tick
    // modifies: this
    // effects:  updates tank, missiles and invaders
    public void update() {
        checkGameOver();
    }

    // Responds to key press codes
    // modifies: this
    // effects:  turns tank, fires missiles and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        // TODO: update keystrokes to match the keystrokes in the game
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            setUp();
        } else if (keyCode == KeyEvent.VK_L && isGameOver) {
            setUp();
        } else if (keyCode == KeyEvent.VK_X && isGameOver) {
            System.exit(0);
        } else {
            currentGuess.keyPressed(keyCode);
        }
    }

    // Exercise: fill in the documentation for this method
    public boolean isOver() {
        return isGameOver;
    }

    // Sets / resets the game
    // modifies: this
    // effects:  clears list of missiles and invaders, initializes tank
    private void setUp() {
        wordHistory.clear();
        isGameOver = false;
    }

    // Is game over? (Has an invader managed to land?)
    // modifies: this
    // effects:  if an invader has landed, game is marked as
    //           over and lists of invaders & missiles cleared
    private void checkGameOver() {
        if (getCurrentWord().isWordSolved()) {
            isGameOver = true;
            // TODO: add stop timer
        }
    }

    // REQUIRES: checkGameOver() has been called and is true, R has been pressed to restart
    // MODIFIES: this
    // EFFECTS:  clears list of game objects (words)
    public void restartGame() {
        // TODO: duplicated code from setUp()
        isGameOver = false;
        wordHistory = new WordHistory();
        actualWord = new Guessing(wordHistory);
        // use an empty string as the current guess
        currentGuess = new Word("", actualWord, wordHistory);
        solveTimer = new SolveTimer();
    }

    public String getTimeElapsed() {
        if (!isGameOver) {
            timeElapsedCache = solveTimer.getTimeElapsed();
        }
        return timeElapsedCache;
    }

    public ArrayList<Word> getWordHistory() {
        return wordHistory.getImmutableWordHistory();
    }

    public Word getCurrentWord() {
        return currentGuess;
    }
}
