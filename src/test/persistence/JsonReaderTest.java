package persistence;

import model.Guessing;
import model.SolveTimer;
import model.WordGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Large extent of class taken/applied from JSONReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {
    WordGame wg;
    JsonReader jsonReader;
    String JSON_STORE = "./data/testReadHistory.json";

    @BeforeEach
    void runBefore() {
        wg = new WordGame();
        jsonReader = new JsonReader(JSON_STORE, wg);
    }

    // test read for JsonReader
    @Test
    void testRead() {
        try {
            jsonReader.read();
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

}