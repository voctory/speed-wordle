package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordHistoryTest {
    WordHistory history;

    @BeforeEach
    public void runBefore() {
        history = new WordHistory();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, history.display().size());
    }

    @Test
    public void testDisplay() {
        ArrayList<String> output = new ArrayList<>();
        assertEquals(output, history.display());
    }

    @Test
    public void testAddToHistory() {
        assertEquals(0, history.display().size());

        history.addToHistory("String");
        assertEquals("String", history.display().get(0));

        history.addToHistory("Word");
        assertEquals("Word", history.display().get(1));
    }
}