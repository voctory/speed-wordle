package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static model.Guessing.*;
import static org.junit.jupiter.api.Assertions.*;

class GuessingTest {
    protected Guessing guess;

    @BeforeEach
    public void runBefore() {
        guess = new Guessing();
        // necessary for consistent testing; otherwise, word is randomized
        guess.setChosenWord("whose");
    }

    @Test
    public void testSetChosenWord() {
        // Confirm that the word can be changed
        String word = guess.getChosenWord();
        assertEquals("whose", word);

        guess.setChosenWord("where");
        word = guess.getChosenWord();
        assertEquals("where", word);

        // confirm that the letters are also changed as expected
        String[] split = word.split("");
        ArrayList<String> characters = guess.getChosenWordLetters();
        assertEquals(characters, new ArrayList<>(Arrays.asList(split)));
    }

    @Test
    public void testInaccuracy() {
        // only need to check that the string is stored properly.
        WordHistory wordHistory = guess.getWordHistory();
        assertEquals(0, wordHistory.display().size());

        guess.inaccuracy("waldo");

        // compare array lists
        assertEquals(1, wordHistory.display().size());

        guess.inaccuracy("water");
        assertEquals(2, wordHistory.display().size());
    }

    @Test
    public void testIsValid() {
        // checks that the length of a string is matching
        assertFalse(guess.isValid("wall"));
        assertTrue(guess.isValid("water"));
        assertFalse(guess.isValid("letter"));
    }

    @Test
    public void testIsCorrect() {
        assertTrue(guess.isCorrect("whose"));
        assertFalse(guess.isCorrect("water"));
    }

    @Test
    public void testCompareStringArrays() {
        String outputString = guess.compareStringArrays("which");
        String expected = ANSI_GREEN + "w" + ANSI_RESET +
                ANSI_GREEN + "h" + ANSI_RESET +
                ANSI_RED + "i" + ANSI_RESET +
                ANSI_RED + "c" + ANSI_RESET +
                ANSI_YELLOW + "h" + ANSI_RESET;

        assertEquals(expected, outputString);
    }

    @Test
    public void testDisplay() {
        ArrayList<String> output = new ArrayList<>();
        assertEquals(output, guess.display());
    }
}