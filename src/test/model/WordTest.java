package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
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
    void testConstructorWithList() {
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
        assertEquals(false, word.isWordSolved());
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
    void testKeyPressedBackspace() {
        word.keyPressed(8);
        assertEquals("test", word.getWord());
    }

    // test keyPressed with keyCode == 10 and word is solved
    @Test
    void testKeyPressedEnter() {
        word.keyPressed(10);
        assertEquals("", word.getWord());
    }

    // test clear for Word
    @Test
    void testClear() {
        word.clear();
        assertEquals("tests", word.getWord());
    }

    // test isWordSolved
    @Test
    void testIsWordSolvedTrue() {
        word.keyPressed(10);
        assertEquals(false, word.isWordSolved());
    }
}
