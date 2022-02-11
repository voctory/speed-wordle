package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Guessing {
    private String chosenWord;
    private ArrayList<String> chosenWordLetters;
    private WordHistory wordHistory;

    private static final int WORD_LENGTH = 5;

    // This list can be massively expanded on with data imports during Phase 2.
    private static final List<String> WORD_LIST = Arrays.asList(
            "which", "whose", "there", "their", "about", "would", "these",
            "other", "words", "could", "write", "first", "water", "after");

    // ANSI colours referenced from the following:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    // MODIFIES: this
    // EFFECTS: from the hard-coded words list, choose a random one and set it as the chosen word.
    //    also, separate the characters of that word and instantiates a new word history
    public Guessing() {
        Random rand = new Random();
        setChosenWord(WORD_LIST.get(rand.nextInt(WORD_LIST.size())));
        wordHistory = new WordHistory();
    }

    // ONLY used for JUnit Test purposes. User does not have access.
    // MODIFIES: this
    // EFFECTS: setter for chosen word and separates the letters
    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
        this.chosenWordLetters = stringToArrayList(chosenWord);
    }

    // EFFECTS: getter for chosen word; used for JUnit tests
    public String getChosenWord() {
        return chosenWord;
    }

    // EFFECTS: getter for chosen word characters array list; used for JUnit tests
    public ArrayList<String> getChosenWordLetters() {
        return chosenWordLetters;
    }

    // EFFECTS: getter for comparing a guessed word to the allotted word length
    public boolean isValid(String guess) {
        // cannot check isValid in Phase 1 of the project; will need to import text file data
        return guess.length() == WORD_LENGTH;
    }

    // REQUIRES: guessed word "guess" is not an exact match to the word
    // MODIFIES: this
    // EFFECTS: compares the letters of a guessed word to the chosen word letters and adds the results
    //   to the word history
    public void inaccuracy(String guess) {
        String outcome = compareStringArrays(guess);
        // add to the word history
        wordHistory.addToHistory(outcome);
        // if this were not console-based, additional string processing would be done here (i.e. formatting)
    }

    // EFFECTS: getter to check if the user's guess was correct; if it was, return true. otherwise, false.
    public boolean isCorrect(String guess) {
        return guess.equals(chosenWord);
    }

    // REQUIRES: guess has the same length as the chosen word
    // EFFECTS: uses a for each loop to compare each character in the guessed word, and its matching relation
    // to the chosen word. a character is green if perfectly matched, yellow if in the wrong spot, or red if not present
    // afterward, return a string of the guessed word and its colour-indicated similarities
    public String compareStringArrays(String guess) {
        StringBuilder output = new StringBuilder();
        ArrayList<String> characters = stringToArrayList(guess);
        for (int i = 0; i < characters.size(); i++) {
            // compare the characters in a word
            String character = characters.get(i);
            String chosenWordCharacter = chosenWordLetters.get(i);
            if (character.equals(chosenWordCharacter)) {
                output.append(ANSI_GREEN).append(character).append(ANSI_RESET);
            } else if (chosenWordLetters.contains(character)) {
                // check if string exists in ArrayList.
                // in this case, .contains() checks for equality and NOT identity
                output.append(ANSI_YELLOW).append(character).append(ANSI_RESET);
            } else {
                output.append(ANSI_RED).append(character).append(ANSI_RESET);
            }
        }
        return output.toString();
    }

    // REQUIRES: word is not an empty string
    // EFFECTS: converts a word's individual characters to an array list, then returns that array list
    private ArrayList<String> stringToArrayList(String word) {
        String[] split = word.split("");
        return new ArrayList<>(Arrays.asList(split));
    }

    // EFFECTS: getter for returning the wordHistory linked to this Guessing object
    public ArrayList<String> display() {
        return wordHistory.display();
    }
}
