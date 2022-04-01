package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Chooses a random word and creates a WordHistory to compare and collect character similarities over time
public class Guessing implements Writable {
    private String chosenWord;
    private ArrayList<String> chosenWordLetters;
    private transient WordHistory wordHistory;

    private static final int WORD_LENGTH = 5;
    private static final List<String> WORD_LIST = Arrays.asList(
            "which", "whose", "there", "their", "about", "would", "these",
            "other", "words", "could", "write", "first", "water", "after");

    // ANSI colours referenced from the following:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // MODIFIES: this
    // EFFECTS: from the hard-coded words list, choose a random one and set it as the chosen word.
    //    also, separate the characters of that word and instantiates a new word history
    public Guessing(WordHistory wh) {
        Random rand = new Random();
        setChosenWord(WORD_LIST.get(rand.nextInt(WORD_LIST.size())));
        this.wordHistory = wh;
    }

    // MODIFIES: this
    // EFFECTS: setter for chosen word and to decompose the letters
    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
        this.chosenWordLetters = stringToArrayList(chosenWord);
        EventLog.getInstance().logEvent(new Event("Set chosen word to " + chosenWord + "."));
    }

    // EFFECTS: getter for chosen word
    public String getChosenWord() {
        return chosenWord;
    }

    // EFFECTS: getter for chosen word characters array list
    public ArrayList<String> getChosenWordLetters() {
        return chosenWordLetters;
    }

    // EFFECTS: getter for comparing a guessed word to the allotted word length
    public boolean isValid(String guess) {
        // cannot check isValid in Phase 1 of the project; will need to import text file data
        return guess.length() == WORD_LENGTH;
    }

    // EFFECTS: getter to check if the user's guess was correct; if it was, return true. otherwise, false.
    public boolean isCorrect(String guess) {
        return guess.equals(chosenWord);
    }

    // REQUIRES: word is not an empty string
    // EFFECTS: converts a word's individual characters to an array list, then returns that array list
    private ArrayList<String> stringToArrayList(String word) {
        String[] split = word.split("");
        return new ArrayList<>(Arrays.asList(split));
    }

    // EFFECTS: indicates that Guessing is transient, not to be saved to file directly (hence, null)
    @Override
    public JSONObject toJson() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: restores the WordHistory and the chosen word from a restored game state
    public void fromJson(String actualWord, WordHistory wh) {
        // find chosenWord in JSON and set it as the chosenWord
        // find chosenWordLetters in JSON and set it as the chosenWordLetters
        // find wordHistory in JSON and set it as the wordHistory
        chosenWord = actualWord;
        chosenWordLetters = stringToArrayList(chosenWord);
        wordHistory = wh;
    }
}
