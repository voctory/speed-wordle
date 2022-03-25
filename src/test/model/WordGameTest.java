package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordGameTest {
    private WordGame wg;

    @BeforeEach
    void runBefore() {
        wg = new WordGame();
    }

    @Test
    void testConstructor() {
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(0, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testKeyPressed() {
        wg.keyPressed('a');
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(1, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testKeyPressed2() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(2, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testKeyPressed3() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(3, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testKeyPressed4() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        wg.keyPressed('d');
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(4, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testKeyPressed5() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        wg.keyPressed('d');
        wg.keyPressed('e');
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(5, wg.getCurrentWord().getWord().length());
    }

    // make a test for restartGame in WordGame
    @Test
    void testRestartGame() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        wg.keyPressed('d');
        wg.keyPressed('e');
        wg.restartGame();
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(0, wg.getCurrentWord().getWord().length());
    }

    // test isOver in WordGame with invalid input
    @Test
    void testIsOverNonAnswer() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        wg.keyPressed('d');
        wg.keyPressed('e');
        assertEquals(false, wg.isOver());
    }

    // test update; nothing should change
    @Test
    void testUpdate() {
        wg.keyPressed('a');
        wg.keyPressed('b');
        wg.keyPressed('c');
        wg.keyPressed('d');
        wg.keyPressed('e');
        wg.update();
        assertEquals(0, wg.getWordHistory().size());
        assertEquals(5, wg.getCurrentWord().getWord().length());
    }

    @Test
    void testGetTimeElapsed() {
        // i.e., string should not be empty
        assertTrue(0 < wg.getTimeElapsed().length());
        assertFalse(wg.getTimeElapsed().isEmpty());
    }
}
