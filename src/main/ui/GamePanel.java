package ui;

import model.Letter;
import model.Word;
import model.WordGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private WordGame game;
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static final String SAVE = "TAB to save";
    private static final String RELOAD = "ESC to reload";
    private static final Color WORDLE_THEME = new Color(0x121213);

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(WordGame g) {
        setPreferredSize(new Dimension(WordGame.WIDTH, WordGame.HEIGHT));
        setBackground(WORDLE_THEME);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
        if (game.isOver()) {
            gameOver(g);
        } else {
            drawSaveAndReload(g);
        }
    }

    private void drawSaveAndReload(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        FontMetrics fm = g.getFontMetrics();
        leftSideString(SAVE, g, fm, WordGame.HEIGHT / 2);
        leftSideString(RELOAD, g, fm, WordGame.HEIGHT / 2 + 50);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        JPanel allWords = new JPanel(new GridBagLayout());
        drawWordGuesses(g, allWords);
        drawCurrentGuess(g, allWords);
        add(allWords);
    }

    // Draw the guessed words so far
    // modifies: g
    // effects:  draws the tank onto g
    private void drawWordGuesses(Graphics g, JPanel allWords) {
        JPanel historyRow = new JPanel(new GridBagLayout());
        ArrayList<Word> words = game.getWordHistory();
        for (Word w : words) {
            drawWord(w, g, historyRow, words.indexOf(w));
        }

        GridBagConstraints constraintsRow = new GridBagConstraints();
        constraintsRow.gridy = 0;
        allWords.add(historyRow, constraintsRow);
    }

    // Draw the guessed words so far
    // modifies: g
    // effects:  draws the tank onto g
    private void drawCurrentGuess(Graphics g, JPanel allWords) {
        Word currentWord = game.getCurrentWord();
        drawWord(currentWord, g, allWords);
    }

    // Draw the *guessed* word
    // modifies: g
    // effects:  draws the word onto g
    private void drawWord(Word w, Graphics g, JPanel allWords) {
        JPanel wordRow = new JPanel(new GridBagLayout());

        for (int i = 0; i < w.getLetters().size(); i++) {
            drawLetter(w.getLetters().get(i), g, wordRow);
        }
        for (int i = 0; i < 5 - w.getLetters().size(); i++) {
            drawLetter(new Letter(" "), g, wordRow);
        }

        // processing occurs here for the guessed word
        GridBagConstraints constraintsRow = new GridBagConstraints();
        constraintsRow.gridy = 1;

        allWords.add(wordRow, constraintsRow);
    }

    // Draw a word from the history
    // modifies: g
    // effects:  draws the word onto g
    private void drawWord(Word w, Graphics g, JPanel historyRow, int rowIndex) {
        JPanel wordRow = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsRow = new GridBagConstraints();
        constraintsRow.gridy = rowIndex;

        for (Letter l : w.getLetters()) {
            drawLetter(l, g, wordRow);
        }
        historyRow.add(wordRow, constraintsRow);
    }

    // Draw the letter
    private void drawLetter(Letter letter, Graphics g, JPanel wordRow) {
        GridBagConstraints letterGrid = new GridBagConstraints();

        Insets zeroInset = new Insets(0, 0, 0, 0);
        Font monospace = new Font(Font.MONOSPACED, Font.BOLD, 18);

        letterGrid.ipady = 20;
        letterGrid.insets = zeroInset;

        JButton b;

        b = new JButton(letter.getLetter());
        b.setFont(monospace);
        b.setFocusable(false);
        b.setBackground(letter.getColor());
        b.setOpaque(true);

        wordRow.add(b, letterGrid);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, WordGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, WordGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position posY
    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (WordGame.WIDTH - width) / 15, posY);
    }

    private void leftSideString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (WordGame.WIDTH - width) / 15, posY);
    }
}
