package model;

import java.awt.*;

// Represents an individual letter in the word, with its location and styling properties
public class Letter {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 20;

    private Color color;
    private int letterIndexInWord;
    private char letter;

    // MODIFIES: this
    // EFFECTS: construct a letter in an already guessed word, places letter at position (x, Y_POS).
    public Letter(char letter, int x, Color color) {
        this.letter = Character.toUpperCase(letter);
        this.letterIndexInWord = x;
        this.color = color;
    }

    // MODIFIES: this
    // EFFECTS: Construct a letter in an unguessed word, tentative
    public Letter(String letter) {
        // convert to upper case
        this.letter = Character.toUpperCase(letter.charAt(0));
        this.letterIndexInWord = 0;
        this.color = Color.DARK_GRAY;
    }

    // EFFECTS: returns the color of the letter for GUI display
    public Color getColor() {
        return color;
    }

    // EFFECTS: returns the actual letter string
    public String getLetter() {
        return String.valueOf(letter);
    }
}
