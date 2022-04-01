package persistence;

import model.Event;
import model.EventLog;
import model.Guessing;
import org.json.JSONObject;
import ui.WordAppMain;


import java.io.*;

// Large extent of class taken/applied from JSONWriter class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of a WordGame to a persistence file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
        EventLog.getInstance().logEvent(new Event("Opening and writing to " + WordAppMain.JSON_STORE));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of guessing game to file
    public void write(JSONObject gameState) {
        saveToFile(gameState.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Saved current game to " + WordAppMain.JSON_STORE));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // EFFECTS: collects error and logs it
    public void error() {
        EventLog.getInstance().logEvent(new Event("Unable to write to file: " + WordAppMain.JSON_STORE));
    }
}
