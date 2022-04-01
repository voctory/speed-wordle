package ui;

import java.io.FileNotFoundException;

// Start the program and GUI here!
public class Main {
    // EFFECTS: initializes the app
    public static void main(String[] args) {
        // Try-catch construct taken/applied from Main class in
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
        try {
            new WordAppMain();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run Speed Wordle: storage file not found");
        }
    }
}
