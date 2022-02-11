package ui;

import model.Timer;
import model.Guessing;
import model.WordHistory;

import java.util.Scanner;

public class WordApp {
    private Scanner input;
    private boolean gameActive;

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
                while (gameActive) {
                    WordHistory.display();
                    // in this case, operation is used to communicate the guessed word.
                    operation = input.next();
                    if (Guessing.isCorrect(operation)) {
                        finishGame();
                    } else {
                        String outcome = Guessing.inaccuracy(operation);
                        System.out.println(outcome);
                    }
                }
            } else {
                break;
            }
        }

        System.out.println("Come back anytime :)");
    }


    private void activateGame() {
        gameActive = true;
        Timer.start();
    }

    private void finishGame() {
        gameActive = false;
        String time = Timer.stop();
        System.out.println("Congratulations!");
        System.out.println("Your time was: " + time);
        System.out.println("We'd love to play another game with you!");
    }
}
