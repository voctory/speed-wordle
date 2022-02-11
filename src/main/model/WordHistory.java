package model;

import java.util.ArrayList;

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
}
