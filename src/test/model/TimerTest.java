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
    public void testGetTimeElapsed() {
        // cannot test dynamic values of timer.
        // instead, ensure that the string is formatted
        String answer = timer.getTimeElapsed();
        assertTrue(answer.length() > 0);
    }

    @Test
    public void testSetStartTime() {
        timer.setStartTime(1646377418990L);
        assertEquals(1646377418990L, timer.getTime());
    }
}