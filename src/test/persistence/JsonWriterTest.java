package persistence;

import model.Guessing;
import model.WordGame;
import model.WordHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Large extent of class taken/applied from JSONWritertest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    String JSON_STORE = "./data/testWriteHistory.json";
    JsonWriter jsonWriter;
    WordGame wordGame;

    @BeforeEach
    public void runBefore() {
        jsonWriter = new JsonWriter(JSON_STORE);
        wordGame = new WordGame();
    }

    @Test
    public void open() {
        try {
            jsonWriter.open();
        } catch (IOException e) {
            fail("IOException");
        }
    }

    // test toJson of WordGame
    @Test
    public void testToJson() {
        try {
            jsonWriter.open();
            jsonWriter.write(wordGame.toJson());
            jsonWriter.close();
        } catch (IOException e) {
            fail("IOException");
        }
    }

}