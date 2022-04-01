package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordTest {
    WordHistory history;
    Word word;
    Guessing guess;
    private static final String CORRECT_WORD = "there";

    @BeforeEach
    public void runBefore() {
        history = new WordHistory();
        guess = new Guessing(history);
        guess.setChosenWord(CORRECT_WORD);
        word = new Word("tests", guess, history);
    }

    @Test
    public void testGetWord() {
        assertEquals("tests", word.getWord());
    }

    @Test
    public void testGetLetters() {
        assertEquals("T", word.getLetters().get(0).getLetter());
        assertEquals("E", word.getLetters().get(1).getLetter());
        assertEquals("S", word.getLetters().get(2).getLetter());
        assertEquals("T", word.getLetters().get(3).getLetter());
    }

    // test setWord
    @Test
    public void testSetWord() {
        word.setWord("write");
        assertEquals("write", word.getWord());
    }

    // test isWordSolved
    @Test
    public void testIsWordSolved() {
        assertFalse(word.isWordSolved());
    }

    @Test
    void testKeyPressedAddLetterAndNoFit() {
        word.keyPressed(78);
        assertEquals("tests", word.getWord());
    }

    @Test
    void testKeyPressedAddLetterAndFit() {
        word = new Word("test", guess, history);
        word.keyPressed(78);
        assertEquals("testn", word.getWord());
    }

    // test keyPressed with keyCode == 8
    @Test
    void testKeyPressedBackspaceNonEmptyWord() {
        word.keyPressed(8);
        assertEquals("test", word.getWord());
    }

    @Test
    void testKeyPressedBackspaceEmptyWord() {
        word = new Word("", guess, history);
        word.keyPressed(8);
        assertEquals("", word.getWord());
    }

    // test keyPressed with keyCode == 10 and word is solved
    @Test
    void testKeyPressedEnter5LetterWord() {
        word.keyPressed(10);
        assertEquals("", word.getWord());
    }

    @Test
    void testKeyPressedEnter4LetterWord() {
        word = new Word("test", guess, history);
        word.keyPressed(10);
        assertFalse(word.isWordSolved());
    }

    // test clear for Word
    @Test
    void testClear() {
        word.clear();
        assertEquals("tests", word.getWord());
    }

    // test isWordSolved
    @Test
    void testIsWordSolvedFalse() {
        word.keyPressed(10);
        assertFalse(word.isWordSolved());
    }

    // test isWordSolved
    @Test
    void testIsWordSolvedTrue() {
        word.setWord(CORRECT_WORD);
        word.keyPressed(10);
        assertTrue(word.isWordSolved());
    }

    // test keyPressed method with stillGuessing being true
    @Test
    void testKeyPressedStillGuessing() {
        word.keyPressed(10);
        assertFalse(word.isWordSolved());
    }
}
