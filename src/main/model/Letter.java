package model;

import java.awt.*;

public class Letter {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 20;

    private Color color;
    private int letterIndexInWord;
    private char letter;

    // Construct a letter in an already guessed word
    // effects: places letter at position (x, Y_POS).
    public Letter(char letter, int x, Color color) {
        this.letter = Character.toUpperCase(letter);
        this.letterIndexInWord = x;
        this.color = color;
    }

    // Construct a letter in an unguessed word, tentative
    public Letter(String letter) {
        // convert to upper case
        this.letter = Character.toUpperCase(letter.charAt(0));
        this.letterIndexInWord = 0;
        this.color = Color.BLUE;
    }

    public Color getColor() {
        return color;
    }

    public String getLetter() {
        return String.valueOf(letter);
    }
}
