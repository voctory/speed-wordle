package persistence;

import model.Guessing;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Guessing gg = new Guessing();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Guessing gg = new Guessing();
            gg.setChosenWord("water");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGuessing.json");
            writer.open();
            writer.write(gg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGuessing.json");
            gg = reader.read();
            assertEquals(0, gg.getWordHistory().display().size());
            assertEquals("water", gg.getChosenWord());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Guessing gg = new Guessing();
            gg.setChosenWord("words");
            // adding inaccurate guesses to the Word History
            gg.inaccuracy("twist");
            gg.inaccuracy("turns");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGuessing.json");
            writer.open();
            writer.write(gg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGuessing.json");
            gg = reader.read();
            assertEquals(2, gg.getWordHistory().display().size());
            assertEquals("words", gg.getChosenWord());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}