package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class WordGame implements Writable {
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

    // Reload WordGame from persisted save state
    // requires: json is a valid JSON object
    public void reload(JSONObject json) {
        restartGame();

        isGameOver = json.getBoolean("isGameOver");

        Gson gson = new Gson();
        String jsonString = json.getString("wordHistory");
        // convert json string to WordHistory object

        Type words = new TypeToken<Collection<Word>>(){}.getType();
        Collection<Word> enums = gson.fromJson(jsonString, words);

        wordHistory.setWordHistory(enums);
        actualWord.fromJson(json.getString("actualWord"), wordHistory);

        // restore solve timer from JSON, convert into long
        solveTimer = new SolveTimer(json.getLong("timeElapsed"));
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("isGameOver", isGameOver);
        json.put("currentGuess", currentGuess.getWord());
        json.put("actualWord", actualWord.getChosenWord());
        json.put("timeElapsed", solveTimer.getTime());
        // convert word history to JSON with GSON

        json.put("wordHistory", wordHistory.toJson());
        return json;
    }
}
