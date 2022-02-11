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
    private Timer timer;

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

    private void instructions() {
        System.out.println("The goal: solve the word as quickly as possible. You are being timed!");
        System.out.println("You can only enter 5-letter words! To exit a game, type 'exit'.");
        System.out.println("Green letter: the letter is in the correct place!");
        System.out.println("Yellow letter: the letter exists in the word, but it is not in the correct place.");
        System.out.println("Red letter: the letter does not exist in the word :(\n");
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
                display();
            }
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
        timer = new Timer();
    }

    private void finishGame() {
        gameActive = false;
        String time = timer.stop();
        System.out.println("Your time was: " + time);
        System.out.println("We'd love to play another game with you!\n");
    }
}
