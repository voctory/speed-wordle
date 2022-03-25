package persistence;

import model.Guessing;
import model.SolveTimer;
import model.WordGame;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Large extent of class taken/applied from JSONReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {

    // TODO: fix
//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
//            WordGame gg = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }

    // TODO: fix
//    @Test
//    void testReaderNoGuesses() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyGuessing.json");
//        try {
//            WordGame gg = reader.read();
//            // TODO: fix test
////            assertEquals(0, gg.getWordHistory().display().size());
//            assertEquals("water", gg.getCurrentWord().getWord());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

//    @Test
//    void testReaderOneGuess() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralGuessing.json");
//        try {
//            WordGame gg = reader.read();
//            // TODO: fix test
////            assertEquals(1, gg.getWordHistory().display().size());
//            assertEquals("water", gg.getCurrentWord().getWord());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderManyGuesses() {
//        JsonReader reader = new JsonReader("./data/testReaderManyGuesses.json");
//        try {
//            WordGame gg = reader.read();
//            // TODO: fix test
////            assertEquals(2, gg.getWordHistory().display().size());
//            assertEquals("words", gg.getCurrentWord().getWord());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    // also contains testParseTime, a helper function
//    @Test
//    public void testReadTime() {
//        JsonReader reader = new JsonReader("./data/testReaderManyGuesses.json");
//        try {
//            reader.readTime();
//            assertEquals(1646371815799L, SolveTimer.getTime());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

}