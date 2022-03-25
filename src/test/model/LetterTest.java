package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterTest {
    Letter letterHidden;
    Letter letterRevealed;

    @BeforeEach
    void runBefore() {
        // with a letter
        letterHidden = new Letter("A");
        letterRevealed = new Letter('B', 3, Color.GREEN);
    }

    @Test
    void testLetterHiddenConstructor() {
        assertEquals("A", letterHidden.getLetter());
        assertEquals(Color.DARK_GRAY, letterHidden.getColor());
    }

    @Test
    void testLetterRevealedConstructor() {
        assertEquals("B", letterRevealed.getLetter());
        assertEquals(Color.GREEN, letterRevealed.getColor());
    }
}
