package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    protected Timer timer;

    @BeforeEach
    public void runBefore() {
        timer = new Timer();
    }

    @Test
    public void testConstructor() {
        // indicates that a time was successfully declared
        assertTrue(timer.getTime() > 0);
    }

    @Test
    public void testStop() {
        // cannot test dynamic values of timer.
        // instead, ensure that the string is formatted
        String answer = timer.stop();
        assertTrue(answer.length() > 0);
    }
}