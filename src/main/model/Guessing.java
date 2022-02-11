package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Guessing {
    private static String chosenWord;
    private static ArrayList<String> chosenWordLetters;
    private static WordHistory wordHistory;

    public Guessing() {
        // TODO: import random, but valid 5 letter words. perhaps another library?
        this.chosenWord = "whose";
        this.chosenWordLetters = stringToArrayList(chosenWord);
        wordHistory = new WordHistory();
    }

    // ONLY used for JUnit Test purposes. User does not have access.
    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public static boolean isValid(String guess) {
        // cannot check isValid in Phase 1 of the project; will need to import text file data
        if (guess.length() != 5) {
            return false;
        }
        return true;
    }

    // REQUIRES: guessed word "guess" is not an exact match to the word
    public static void inaccuracy(String guess) {
        String outcome = compareStringArrays(guess);
        // add to the word history
        wordHistory.addToHistory(outcome);
        // if this were not console-based, additional string processing would be done here (i.e. formatting)
    }

    // EFFECTS: check if the user's guess was correct; if it was, return true. otherwise, false.
    public static boolean isCorrect(String guess) {
        if (guess.equals(chosenWord)) {
            return true;
        }
        return false;
    }

    public static String compareStringArrays(String guess) {
        String output = "";
        ArrayList<String> characters = stringToArrayList(guess);
        for (int i = 0; i < characters.size(); i++) {
            // compare the characters in a word
            String character = characters.get(i);
            String chosenWordCharacter = chosenWordLetters.get(i);
            if (character.equals(chosenWordCharacter)) {
                output = output + " !" + character + "! ";
            } else if (chosenWordLetters.contains(character)) {
                // check if string exists in ArrayList.
                // in this case, .contains() checks for equality and NOT identity
                output = output + " /" + character + "/ ";
            } else if (!character.equals(chosenWordCharacter)) {
                output = output + " *" + character + "* ";
            }
        }
        return output;
    }

    private static ArrayList<String> stringToArrayList(String word) {
        String[] split = word.split("");
        ArrayList<String> characters = new ArrayList<>(Arrays.asList(split));
        return characters;
    }


}
