package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import ui.WordAppMain;

// Large extent of class taken/applied from JSONReader class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads a persisted WordGame game from the JSON data stored in file
public class JsonReader {
    private String source;
    private WordGame wordGame;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source, WordGame gg) {
        this.wordGame = gg;
        this.source = source;
    }

    // EFFECTS: reads guessing game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseGuessing(jsonObject, wordGame);
        EventLog.getInstance().logEvent(new Event("Loaded old game from " + WordAppMain.JSON_STORE));
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // MODIFIES: this, WordGame
    // EFFECTS: parses word game from JSON object and returns it
    private void parseGuessing(JSONObject jsonObject, WordGame gg) {
        gg.reload(jsonObject);
    }

    // EFFECTS: collects error and logs it
    public void error() {
        EventLog.getInstance().logEvent(new Event("Unable to read from file: " + WordAppMain.JSON_STORE));
    }
}
