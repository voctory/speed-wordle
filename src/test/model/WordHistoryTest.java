package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordHistoryTest {
    WordHistory history;
    Word word;
    Guessing guess;

    @BeforeEach
    public void runBefore() {
        history = new WordHistory();
        guess = new Guessing(history);
        word = new Word("tests", guess, history);
    }

    @Test
    public void testAddWord() {
        history.addToHistory(word);
        assertEquals(1, history.getImmutableWordHistory().size());
    }

    @Test
    public void testAddWordTwice() {
        history.addToHistory(word);
        history.addToHistory(word);
        assertEquals(2, history.getImmutableWordHistory().size());
    }

    // clears the history
    @Test
    public void testClearHistory() {
        history.addToHistory(word);
        history.clear();
        assertEquals(0, history.getImmutableWordHistory().size());
    }

    // test setWordHistory
    @Test
    public void testSetWordHistory() {
        ArrayList<Word> wordList = new ArrayList<>();
        wordList.add(word);
        history.setWordHistory(wordList);
        assertEquals(1, history.getImmutableWordHistory().size());
    }
}