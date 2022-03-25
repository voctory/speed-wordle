package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Word {
    private String word;
    private List<Letter> letters;
    private Guessing actualWord;
    private boolean stillGuessing;
    private WordHistory wordHistory;

    // for generating guessed words
    public Word(String word, List<Letter> letters, Guessing actualWord, WordHistory wh) {
        this.word = word;
        this.actualWord = actualWord;
        this.wordHistory = wh;
        this.stillGuessing = false;
        wordInitializer();
    }

    // for generating new words; has no letters parameter
    public Word(String word, Guessing actualWord, WordHistory wh) {
        this.word = word;
        this.wordHistory = wh;
        this.actualWord = actualWord;
        this.stillGuessing = true;
        wordInitializer();
    }

    private void wordInitializer() {
        this.letters = new ArrayList<>(word.length());
        deconstructWord();
    }

    private void deconstructWord() {
        Map<String, Integer> hashMap = actualWordHashMap();
        for (int i = 0; i < word.length(); i++) {
            letters.add(new Letter(word.charAt(i), i, determineColor(word.charAt(i), i, hashMap)));
        }
    }

    private Map<String, Integer> actualWordHashMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] words = actualWord.getChosenWord().split("");

        // Init hashmaps for all the letters in the actual word
        for (String actualLetter : words) {

            // Asking whether the HashMap contains the key or not. Will return null if not.

            // Storing the letter as key and its
            // occurrence as value in the HashMap.
            // Incrementing the value if the word
            // is already present in the HashMap.
            hashMap.merge(actualLetter, 1, Integer::sum);
        }
        return hashMap;
    }

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
        } else if (actualWord.getChosenWord().contains("" + letter) && indexIsNotNull && letterStillRemains) {
            hashMap.put(Character.toString(letter), integer - 1);
            return Color.YELLOW;
        }
        return Color.RED;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void clear() {
        letters.clear();
    }

    private boolean getWordsAreEqual() {
        return actualWord.getChosenWord().equals(word);
    }

    public boolean isWordSolved() {
        return !stillGuessing && getWordsAreEqual();
    }

    public void keyPressed(int keyCode) {
        // validate keyCode is a letter
        if (Character.isLetter((char) keyCode) && letters.size() < 5) {
            word += Character.toLowerCase((char) keyCode);
            wordInitializer();
        } else if (keyCode == 8 && word.length() > 0) {
            word = word.substring(0, word.length() - 1);
            wordInitializer();
        } else if (keyCode == 10 && letters.size() == 5) {

            // check if the words are equal
            if (getWordsAreEqual()) {
                this.stillGuessing = false;
            } else {
                Word nowGuessedWord = new Word(word, letters, actualWord, wordHistory);
                wordHistory.addToHistory(nowGuessedWord);
                this.word = "";
            }
            wordInitializer();
        }
    }
}
