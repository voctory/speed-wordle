package ui;

import model.Timer;
import model.Guessing;
import model.WordHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// UI class for the main window frame interaction, containing the game and main menus
public class WordAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/history.json";
    private final Scanner input;
    private boolean gameActive;
    private Guessing guesses;
    private Timer timer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    // Constructor
    // EFFECTS: creates a console scanner, confirms initialization, and redirects to the mainMenu method
    public WordAppUI() throws FileNotFoundException {
        input = new Scanner(System.in);
        System.out.println("MOTUS Word App initialized!");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        // TODO: deprecated
        mainMenu();

        desktop = new JDesktopPane();
    }

    // Inspired by lecture lab: B04 Logging Calculator
    // https://github.students.cs.ubc.ca/CPSC210/B04-SimpleCalculatorStarterLecLab
    // MODIFIES: this
    // EFFECTS: always checks for input operation (true loop) when WordApp is initialized while user not in a game,
    //   breaks out of loop when user does not want to run the application anymore and says goodbye
    private void mainMenu() {
        String operation;

        // In the "main menu", confirm if the user is ready to start a game.
        while (true) {
            String intro = "The timer will immediately start. Ready? ('y' to start a new game' / 'i' for help / "
                    + "'l' to load saved game / any key to quit)";
            System.out.println(intro);
            operation = input.nextLine();
            System.out.println("You selected: " + operation + "\n");

            if (operation.equalsIgnoreCase("Y") || operation.equalsIgnoreCase("yes")) {
                activateGame();
            } else if (operation.equalsIgnoreCase("i")) {
                instructions();
            } else if (operation.equalsIgnoreCase("l") || operation.equalsIgnoreCase("load")) {
                loadGame();
            } else {
                break;
            }
        }

        System.out.println("Come back anytime :)");
    }

    // EFFECTS: prints instructions for user to play the game when requested
    private void instructions() {
        System.out.println("The goal: solve the word as quickly as possible. You are being timed!");
        System.out.println("You can only enter 5-letter words! To exit a game, type 'exit'.");
        System.out.println("\u001B[32mGreen letter\u001B[0m: the letter is in the correct place!");
        System.out.println("\u001B[33mYellow letter\u001B[0m: the letter exists in the word, but it is "
                + "not in the correct place.");
        System.out.println("\u001B[31mRed letter\u001B[0m: the letter does not exist in the word :(");
        System.out.println("And finally, if you would like to see the time elapsed during a game, enter 't'.\n");
    }

    // REQUIRES: game is active
    // MODIFIES: this
    // EFFECTS: constantly checks for word input while game is active; if user wants to exit or types the correct
    //   word, end the game. otherwise, check the word's inaccuracies given that it is 5 characters long
    //   and print the word history
    private void getWordInputs() {
        System.out.println("Enter a 5-letter word to guess! Or to leave and save your game, type in 'exit'.");
        String guess;
        while (gameActive) {
            // in this case, operation is used to communicate the guessed word.
            guess = input.nextLine();
            System.out.println("You entered: " + guess);
            if (guess.equalsIgnoreCase("exit")) {
                System.out.println("Okay, exiting the game early and saving it for later.");
                saveGame();
                finishGame();
            } else if (guesses.isCorrect(guess)) {
                System.out.println("Congrats!");
                finishGame();
            } else {
                if (guess.equalsIgnoreCase("t") || guess.equalsIgnoreCase("time")) {
                    System.out.println(timer.getTimeElapsed());
                } else if (!guesses.isValid(guess)) {
                    System.out.println("You did not enter a valid 5-letter word! Try again.");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the game to be active and instantiates new Guessing and Timer objects
    private void activateGame() {
        gameActive = true;
        guesses = new Guessing(new WordHistory());
        timer = new Timer();
        getWordInputs();
    }

    // MODIFIES: this, Timer
    // EFFECTS: ends the game if the user wants to exit or has correctly determined the word;
    //   return the time taken and tell the user
    private void finishGame() {
        gameActive = false;
        String time = timer.getTimeElapsed();
        System.out.println("Your time was: " + time);
        System.out.println("We'd love to play another game with you!\n");
    }

    // EFFECTS: saves the workroom to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(guesses);
            jsonWriter.close();
            System.out.println("Saved current game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGame() {
        try {
            guesses = jsonReader.read();
            timer = jsonReader.readTime();
            System.out.println("Loaded old game from " + JSON_STORE);
            gameActive = true;
            getWordInputs();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
