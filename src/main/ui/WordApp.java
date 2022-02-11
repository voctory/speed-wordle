package ui;

import model.Timer;
import model.Guessing;
import model.WordHistory;

import java.util.ArrayList;
import java.util.Scanner;

public class WordApp {
    private Scanner input;
    private boolean gameActive;
    private Guessing guesses;

    // EFFECTS: runs the word guessing application
    public WordApp() {
        input = new Scanner(System.in);
        System.out.println("MOTUS Word App initialized.");
        mainMenu();
    }

    // Inspired by lecture lab: Logging Calculator
    // MODIFIES: this
    // EFFECTS: always checks for input when WordApp is initialized, not in a game
    private void mainMenu() {
        String operation;

        // In the "main menu", confirm if the user is ready to start a game.
        while (true) {
            System.out.println("The timer will immediately start. Are you ready? (Y / any key to quit)");
            operation = input.nextLine();
            System.out.println("you selected: " + operation);

            if (operation.equalsIgnoreCase("Y") || operation.equalsIgnoreCase("yes")) {
                activateGame();
                getWordInputs();
            } else {
                break;
            }
        }

        System.out.println("Come back anytime :)");
    }

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
            }
            display();
        }
    }

    private void display() {
        ArrayList<String> history = WordHistory.display();
        for (String guess : history) {
            System.out.println(guess);
        }
    }

    private void activateGame() {
        gameActive = true;
        guesses = new Guessing();
        Timer.start();
    }

    private void finishGame() {
        gameActive = false;
        String time = Timer.stop();
        System.out.println("Your time was: " + time);
        System.out.println("We'd love to play another game with you!\n");
    }
}
