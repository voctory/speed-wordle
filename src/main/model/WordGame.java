package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

// represents a word game and all its properties (words, word history, timer)
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
    // MODIFIES: this
    // EFFECTS: creates a new WordGame object
    public WordGame() {
        wordHistory = new WordHistory();
        actualWord = new Guessing(wordHistory);
        // use an empty string as the current guess
        currentGuess = new Word("", actualWord, wordHistory);
        solveTimer = new SolveTimer();
        timeElapsedCache = "";
    }

    // REQUIRES: json is a valid, non-empty JSON object
    // MODIFIES: this
    // EFFECTS:  loads the WordGame state from the given, persisted JSON object
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

        currentGuess.setWord(json.getString("currentGuess"));

        // restore solve timer from JSON, convert into long
        solveTimer = new SolveTimer(json.getLong("timeElapsed"));
    }

    // MODIFIES: this
    // EFFECTS:  updates word game state, checks if game is over
    public void update() {
        if (getCurrentWord().isWordSolved()) {
            isGameOver = true;
        }
    }

    // Responds to key press codes
    // MODIFIES: this, Word
    // EFFECTS:  turns keyboard input into shortcuts, OR letters and adds them to the current guess
    //           given key pressed code
    public void keyPressed(int keyCode) {
        // TODO: update keystrokes to match the keystrokes in the game
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            setUp();
        } else {
            currentGuess.keyPressed(keyCode);
        }
    }

    // EFFECTS:  returns true if game is over, false otherwise
    public boolean isOver() {
        return isGameOver;
    }

    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS:  clears list of missiles and invaders, initializes tank
    private void setUp() {
        wordHistory.clear();
        isGameOver = false;
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

    // EFFECTS: returns the current game state as a JSON object
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
