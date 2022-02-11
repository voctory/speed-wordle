package model;

import java.util.ArrayList;

public class WordHistory {
    private static ArrayList<String> history;

    public WordHistory() {
        history = new ArrayList<>();
    }

    public static ArrayList<String> display() {
        return history;
    }

    public static void addToHistory(String outcome) {
        history.add(outcome);
    }
}
