package persistence;

import org.json.JSONObject;

// Class taken/applied from Writable class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
