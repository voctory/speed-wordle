package ui;

import java.io.FileNotFoundException;

public class Main {
    // EFFECTS: initializes the app
    public static void main(String[] args) {
        try {
            new WordApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run MOTUS: storage file not found");
        }
    }
}
