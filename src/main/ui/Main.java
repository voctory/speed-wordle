package ui;

import model.EventLog;

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
            e.printStackTrace();
        }

        // EFFECTS: prints the event log to the console on shutdown
        // SOURCE: https://www.baeldung.com/jvm-shutdown-hooks
        Thread printingHook = new Thread(() -> {
            EventLog.getInstance().iterator().forEachRemaining(System.out::println);
        });
        Runtime.getRuntime().addShutdownHook(printingHook);
    }
}
