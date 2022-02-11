package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    }

    @Test
    public void testIsCorrect() {

    }

    @Test
    public void testCompareStringArrays() {

    }
}