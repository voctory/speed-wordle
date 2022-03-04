package model;

import com.google.gson.Gson;

import java.util.ArrayList;

// Maintains a list of guessed words throughout a game session, with string formatting
public class WordHistory {
    private ArrayList<String> history;

    // Constructor
    // EFFECTS: creates a new array list to keep track of the words passed
    public WordHistory() {
        history = new ArrayList<>();
    }

    // REQUIRES: a word has already been guessed such that history is not empty
    // EFFECTS: getter for the array list containing all the guessed words so far
    public ArrayList<String> display() {
        return history;
    }

    // MODIFIES: this
    // EFFECTS: setter to add a new colour-indicated guessed word to the array list
    public void addToHistory(String outcome) {
        history.add(outcome);
    }

    // MODIFIES: this
    // EFFECTS: setter to import persisted word history
    public void setHistory(ArrayList<String> guesses) {
        this.history = guesses;
    }

    // EFFECTS: returns word history array list as JSON object, made possible with GSON (not JSONArray)
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(history);
    }

}
