package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Guessing {
    private String chosenWord;
    private ArrayList<String> chosenWordLetters;
    private WordHistory wordHistory;

    // This list can be massively expanded on with data imports during Phase 2.
    private static final List<String> wordList = Arrays.asList(
            "which", "whose", "there", "their", "about", "would", "these",
            "other", "words", "could", "write", "first", "water", "after");

    // ANSI colours referenced from the following:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public Guessing() {
        // TODO: import random, but valid 5 letter words. perhaps another library?
        Random rand = new Random();
        this.chosenWord = wordList.get(rand.nextInt(wordList.size()));;
        this.chosenWordLetters = stringToArrayList(chosenWord);
        wordHistory = new WordHistory();
    }

    // ONLY used for JUnit Test purposes. User does not have access.
    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public boolean isValid(String guess) {
        // cannot check isValid in Phase 1 of the project; will need to import text file data
        if (guess.length() != 5) {
            return false;
        }
        return true;
    }

    // REQUIRES: guessed word "guess" is not an exact match to the word
    public void inaccuracy(String guess) {
        String outcome = compareStringArrays(guess);
        // add to the word history
        wordHistory.addToHistory(outcome);
        // if this were not console-based, additional string processing would be done here (i.e. formatting)
    }

    // EFFECTS: check if the user's guess was correct; if it was, return true. otherwise, false.
    public boolean isCorrect(String guess) {
        if (guess.equals(chosenWord)) {
            return true;
        }
        return false;
    }

    public String compareStringArrays(String guess) {
        String output = "";
        ArrayList<String> characters = stringToArrayList(guess);
        for (int i = 0; i < characters.size(); i++) {
            // compare the characters in a word
            String character = characters.get(i);
            String chosenWordCharacter = chosenWordLetters.get(i);
            if (character.equals(chosenWordCharacter)) {
                output = output + ANSI_GREEN + character + ANSI_RESET;
            } else if (chosenWordLetters.contains(character)) {
                // check if string exists in ArrayList.
                // in this case, .contains() checks for equality and NOT identity
                output = output + ANSI_YELLOW + character + ANSI_RESET;
            } else if (!character.equals(chosenWordCharacter)) {
                output = output + ANSI_RED + character + ANSI_RESET;
            }
        }
        return output;
    }

    private ArrayList<String> stringToArrayList(String word) {
        String[] split = word.split("");
        return new ArrayList<>(Arrays.asList(split));
    }


}
