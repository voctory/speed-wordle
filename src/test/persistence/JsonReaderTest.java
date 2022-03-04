package persistence;

import model.Guessing;
import model.Timer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Large extent of class taken/applied from JSONReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Guessing gg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoGuesses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGuessing.json");
        try {
            Guessing gg = reader.read();
            assertEquals(0, gg.getWordHistory().display().size());
            assertEquals("water", gg.getChosenWord());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderOneGuess() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGuessing.json");
        try {
            Guessing gg = reader.read();
            assertEquals(1, gg.getWordHistory().display().size());
            assertEquals("water", gg.getChosenWord());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderManyGuesses() {
        JsonReader reader = new JsonReader("./data/testReaderManyGuesses.json");
        try {
            Guessing gg = reader.read();
            assertEquals(2, gg.getWordHistory().display().size());
            assertEquals("words", gg.getChosenWord());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // also contains testParseTime, a helper function
    @Test
    public void testReadTime() {
        JsonReader reader = new JsonReader("./data/testReaderManyGuesses.json");
        try {
            reader.readTime();
            assertEquals(1646371815799L, Timer.getTime());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}