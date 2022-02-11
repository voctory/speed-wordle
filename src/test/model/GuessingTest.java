package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessingTest {
    protected Guessing guess;

    @BeforeEach
    private void runBefore() {
        guess = new Guessing();
        guess.setChosenWord("hello");
    }

    @Test
    private void testSetChosenWord() {

    }

    @Test
    private void testInaccuracy() {

    }

    @Test
    private void testIsCorrect() {

    }

    @Test
    private void testCompareStringArrays() {

    }
}