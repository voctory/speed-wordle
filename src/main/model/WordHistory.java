package model;

import com.google.gson.Gson;

import java.util.ArrayList;

// Maintains a list of guessed words throughout a game session, with string formatting
public class WordHistory {
    private ArrayList<Word> history;

    // Constructor
    // EFFECTS: creates a new array list to keep track of the words passed
    public WordHistory() {
        history = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: setter to add a new colour-indicated guessed word to the array list
    public void addToHistory(Word outcome) {
        history.add(outcome);
        trimHistory();
    }

    // REQUIRES: history is not empty
    // MODIFIES: this
    // EFFECTS: limit history size to 10, remove oldest word
    private void trimHistory() {
        if (history.size() > 10) {
            history.remove(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: setter to import persisted word history
    public void setHistory(ArrayList<Word> guesses) {
        this.history = guesses;
    }

    // EFFECTS: returns word history array list as JSON object, made possible with GSON (not JSONArray)
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(history);
    }

    public void clear() {
        history.clear();
    }

    public ArrayList<Word> getImmutableWordHistory() {
        return new ArrayList<>(history);
    }

    public int getSizeY() {
        return history.size() * Letter.SIZE_Y;
    }
}
