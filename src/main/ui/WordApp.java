package ui;

import model.Timer;
import model.Guessing;

import java.util.ArrayList;
import java.util.Scanner;

public class WordApp {
    private final Scanner input;
    private boolean gameActive;
    private Guessing guesses;
    private Timer timer;

    // Constructor
    // EFFECTS: creates a console scanner, confirms initialization, and redirects to the mainMenu method
    public WordApp() {
        input = new Scanner(System.in);
        System.out.println("MOTUS Word App initialized!");
        mainMenu();
    }

    // Inspired by lecture lab: B04 Logging Calculator
    // MODIFIES: this
    // EFFECTS: always checks for input operation (true loop) when WordApp is initialized while user not in a game,
    //   breaks out of loop when user does not want to run the application anymore and says goodbye
    private void mainMenu() {
        String operation;

        // In the "main menu", confirm if the user is ready to start a game.
        while (true) {
            String intro = "The timer will immediately start. Ready? ('y' to start' / 'i' for help / any key to quit)";
            System.out.println(intro);
            operation = input.nextLine();
            System.out.println("You selected: " + operation + "\n");

            if (operation.equalsIgnoreCase("Y") || operation.equalsIgnoreCase("yes")) {
                activateGame();
                getWordInputs();
            } else if (operation.equalsIgnoreCase("i")) {
                instructions();
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
        System.out.println("Green letter: the letter is in the correct place!");
        System.out.println("Yellow letter: the letter exists in the word, but it is not in the correct place.");
        System.out.println("Red letter: the letter does not exist in the word :(\n");
    }

    // REQUIRES: game is active
    // MODIFIES: this
    // EFFECTS: constantly checks for word input while game is active; if user wants to exit or types the correct
    //   word, end the game. otherwise, check the word's inaccuracies given that it is 5 characters long
    //   and print the word history
    private void getWordInputs() {
        System.out.println("Enter a 5-letter word to guess! Or to leave, type in 'exit'.");
        String guess;
        while (gameActive) {
            // in this case, operation is used to communicate the guessed word.
            guess = input.nextLine();
            System.out.println("You entered: " + guess);
            if (guess.equalsIgnoreCase("exit")) {
                System.out.println("Okay, exiting the game early.");
                finishGame();
            } else if (guesses.isCorrect(guess)) {
                System.out.println("Congrats!");
                finishGame();
            } else {
                if (!guesses.isValid(guess)) {
                    System.out.println("You did not enter a valid 5-letter word! Try again.");
                } else {
                    guesses.inaccuracy(guess);
                }
                display();
            }
        }
    }

    // EFFECTS: for every word in the instantiated word history, print it out first (oldest) to last (newest)
    private void display() {
        ArrayList<String> history = guesses.display();
        for (String guess : history) {
            System.out.println(guess);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the game to be active and instantiates new Guessing and Timer objects
    private void activateGame() {
        gameActive = true;
        guesses = new Guessing();
        timer = new Timer();
    }

    // MODIFIES: this, Timer
    // EFFECTS: ends the game if the user wants to exit or has correctly determined the word;
    //   return the time taken and tell the user
    private void finishGame() {
        gameActive = false;
        String time = timer.stop();
        System.out.println("Your time was: " + time);
        System.out.println("We'd love to play another game with you!\n");
    }
}
