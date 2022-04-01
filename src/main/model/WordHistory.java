package model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

// Maintains a list of guessed words throughout a game session, with string formatting
public class WordHistory {
    private ArrayList<Word> history;

    // Constructor
    // EFFECTS: creates a new array list to keep track of the words passed
    public WordHistory() {
        history = new ArrayList<>();
    }

    // Alternate constructor for restoring save from JSON
    // MODIFIES: this
    // EFFECTS: restores the history from a JSON Object
    public void setWordHistory(Collection enums) {
        history = new ArrayList<Word>(enums);
    }

    // MODIFIES: this
    // EFFECTS: setter to add a new colour-indicated guessed word to the array list
    public void addToHistory(Word outcome) {
        history.add(outcome);
        trimHistory();
    }

    // MODIFIES: this
    // EFFECTS: limit history size to 10, remove oldest word
    private void trimHistory() {
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    // EFFECTS: returns word history array list as JSON object, made possible with GSON (not JSONArray)
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(history);
    }

    // MODIFIES: this
    // EFFECTS: clears the history once a game is over
    public void clear() {
        history.clear();
    }

    // EFFECTS: returns the history of guessed words as an immutable array list
    public ArrayList<Word> getImmutableWordHistory() {
        return new ArrayList<>(history);
    }
}
