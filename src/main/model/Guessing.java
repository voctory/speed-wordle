package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Guessing {
    private String chosenWord;
    private ArrayList<String> characters;

    public Guessing() {
        // TODO: import random, but valid 5 letter words. perhaps another library?
        chosenWord = "whose";
        // TODO: might need to make the characters a separate class? or method
        String[] split = chosenWord.split("");
        characters = new ArrayList<>(Arrays.asList(split));
    }

    // ONLY used for JUnit Test purposes. User does not have access.
    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public static String inaccuracy(String operation) {
        return "";
    }

    // EFFECTS: check if the user's guess was correct; if it was, return true. otherwise, false.
    public static boolean isCorrect(String operation) {
        if (!compareStringArrays(operation)) {
            return false;
        }
        return true;
    }

    public static boolean compareStringArrays(String input) {
        return false; // stub
    }
}
