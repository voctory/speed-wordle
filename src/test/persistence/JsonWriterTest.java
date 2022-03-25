package persistence;

import model.Guessing;
import model.Word;
import model.WordHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Large extent of class taken/applied from JSONWritertest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Guessing gg = new Guessing(new WordHistory());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // TODO: fix test
//    @Test
//    void testWriterEmptyWorkroom() {
//        try {
//            Guessing gg = new Guessing(new WordHistory());
//            gg.setChosenWord("water");
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGuessing.json");
//            writer.open();
//            writer.write(gg);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyGuessing.json");
//            gg = reader.read();
//            // TODO: fix this test
////            assertEquals(0, gg.getWordHistory().display().size());
//            assertEquals("water", gg.getChosenWord());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }

    // TODO: fix test
//    @Test
//    void testWriterGeneralWorkroom() {
//        try {
//            Guessing gg = new Guessing(new WordHistory());
//            gg.setChosenWord("words");
//            // adding inaccurate guesses to the Word History
//            // TODO: restore
////            gg.inaccuracy("twist");
////            gg.inaccuracy("turns");
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGuessing.json");
//            writer.open();
//            writer.write(gg);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralGuessing.json");
//            gg = reader.read();
//            // TODO: fix this test
////            assertEquals(2, gg.getWordHistory().display().size());
//            assertEquals("words", gg.getChosenWord());
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
}