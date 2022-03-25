package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Guessing;
import model.SolveTimer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Word;
import model.WordHistory;
import org.json.*;

// Large extent of class taken/applied from JSONReader class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads guessing game from JSON data stored in file
public class JsonReader {
    private String source;
    private WordHistory wordHistory;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        this.wordHistory = new WordHistory();
    }

    // EFFECTS: reads guessing game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Guessing read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGuessing(jsonObject);
    }

    // EFFECTS: reads guessing game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SolveTimer readTime() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTime(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses guessing game from JSON object and returns it
    private Guessing parseGuessing(JSONObject jsonObject) {
        Guessing gg = new Guessing(wordHistory);
        restoreGameState(gg, jsonObject);
        return gg;
    }

    // EFFECTS: parses old game state time from JSON object and returns it
    private SolveTimer parseTime(JSONObject jsonObject) {
        SolveTimer timer = new SolveTimer();
        timer.setStartTime(jsonObject.getJSONObject("game").getLong("time"));
        return timer;
    }


    // Inspired by Baeldung's Gson ArrayList deserializer: JSONArray doesn't work for this use-case :(
    // https://www.baeldung.com/gson-list
    // MODIFIES: gg
    // EFFECTS: parses old game state properties from JSON object and adds them to guessing game
    private void restoreGameState(Guessing gg, JSONObject jsonObject) {
        JSONObject jsonGameState = jsonObject.getJSONObject("game");

        gg.setChosenWord(jsonGameState.getString("word"));

        Gson gson = new Gson();
        String historyString = jsonGameState.getString("history");

        Type listOfHistoryReference = new TypeToken<ArrayList<Word>>() {}.getType();

        ArrayList<Word> outputHistory = gson.fromJson(historyString, listOfHistoryReference);

        // need to restore word history from current
        wordHistory.setHistory(outputHistory);
    }
}
