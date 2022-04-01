package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// class that represents a word and its letters for a Wordle game
public class Word {
    private String word;
    private List<Letter> letters;
    private transient Guessing actualWord;
    private boolean stillGuessing;
    private transient WordHistory wordHistory;

    // MODIFIES: this
    // EFFECTS: constructor for generating already guessed words (in the WordHistory)
    public Word(String word, List<Letter> letters, Guessing actualWord, WordHistory wh) {
        this.word = word;
        this.actualWord = actualWord;
        this.wordHistory = wh;
        this.stillGuessing = false;
        wordInitializer();
        EventLog.getInstance().logEvent(new Event("Word added to history: " + word));
    }

    // MODIFIES: this
    // EFFECTS: for generating new words (i.e. currently guessing)
    public Word(String word, Guessing actualWord, WordHistory wh) {
        this.word = word;
        this.wordHistory = wh;
        this.actualWord = actualWord;
        this.stillGuessing = true;
        wordInitializer();
    }

    // MODIFIES: this
    // EFFECTS: initializes the letters of the word
    private void wordInitializer() {
        this.letters = new ArrayList<>(word.length());
        deconstructWord();
    }

    // MODIFIES: this
    // EFFECTS: deconstructs the word into letters
    private void deconstructWord() {
        Map<String, Integer> hashMap = actualWordHashMap();
        for (int i = 0; i < word.length(); i++) {
            letters.add(new Letter(word.charAt(i), i, determineColor(word.charAt(i), i, hashMap)));
        }
    }

    // MODIFIES: this
    // EFFECTS: returns the hashmap of the actual word
    private Map<String, Integer> actualWordHashMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] words = actualWord.getChosenWord().split("");

        // Init hashmaps for all the letters in the actual word
        for (String actualLetter : words) {
            hashMap.merge(actualLetter, 1, Integer::sum);
        }
        return hashMap;
    }

    // MODIFIES: this
    // EFFECTS: determines the color of the letter
    private Color determineColor(char letter, int index, Map<String, Integer> hashMap) {
        // SOURCE: Wordle hashmap logic
        // SOURCE: https://www.geeksforgeeks.org/java-program-to-find-the-occurrence-of-words-in-a-string-using-hashmap/


        Integer integer = hashMap.get(Character.toString(letter));
        boolean indexIsNotNull = integer != null;
        boolean letterStillRemains = indexIsNotNull && integer > 0;

        if (stillGuessing) {
            return Color.GRAY;
        } else if (letter == actualWord.getChosenWord().charAt(index)) {
            hashMap.put(Character.toString(letter), integer - 1);
            return Color.GREEN;
        } else if (letterStillRemains) {
            hashMap.put(Character.toString(letter), integer - 1);
            return Color.YELLOW;
        }
        return Color.DARK_GRAY;
    }

    // EFFECTS: getter to return the word
    public String getWord() {
        return word;
    }

    // EFFECTS: setter to change the word
    public void setWord(String word) {
        this.word = word;
    }

    // EFFECTS: getter to return the letters in a list
    public List<Letter> getLetters() {
        return letters;
    }

    // EFFECTS: setter to remove all letters
    public void clear() {
        letters.clear();
    }

    // EFFECTS: checker to compare the actual word with the current guess
    private boolean getWordsAreEqual() {
        return actualWord.getChosenWord().equals(word);
    }

    // EFFECTS: getter to return the guessing state of the current Word
    public boolean isWordSolved() {
        return !stillGuessing;
    }

    // MODIFIES: this
    // EFFECTS: direct interface to type letters, delete letters, or enter to check if the word is solved
    public void keyPressed(int keyCode) {
        // validate keyCode is a letter
        if (Character.isLetter((char) keyCode) && letters.size() < 5) {
            word += Character.toLowerCase((char) keyCode);
            wordInitializer();
            EventLog.getInstance().logEvent(new Event("Letter added to word: " + (char) keyCode));
        } else if (keyCode == 8 && word.length() > 0) {
            word = word.substring(0, word.length() - 1);
            EventLog.getInstance().logEvent(new Event("Letter removed from word."));
            wordInitializer();
        } else if (keyCode == 10 && letters.size() == 5) {
            // check if the words are equal
            EventLog.getInstance().logEvent(new Event("Word has been entered by user."));
            if (getWordsAreEqual()) {
                EventLog.getInstance().logEvent(new Event("Word has been solved."));
                this.stillGuessing = false;
            } else {
                EventLog.getInstance().logEvent(new Event("Word is incorrect."));
                Word nowGuessedWord = new Word(word, letters, actualWord, wordHistory);
                wordHistory.addToHistory(nowGuessedWord);
                this.word = "";
            }
            wordInitializer();
        }
    }
}
