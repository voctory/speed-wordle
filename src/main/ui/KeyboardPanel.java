package ui;

import javax.swing.*;   // JFrame, JPanel, JLabel, JButton
import java.awt.*;      // GridBagLayout, GridBagConstraints, Insets, Font

// Displays a keyboard for user to see what options they can type on-screen. NOT CLICKABLE, JUST FOR DISPLAY
// SOURCE: https://stackoverflow.com/questions/24622279/laying-out-a-keyboard-in-swing
public class KeyboardPanel extends JPanel {
    private static final String[][] key = {
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L",},
            {"Enter", "Z", "X", "C", "V", "B", "N", "M", "Delete"},
    };

    // Constructor
    // MODIFIES: this, JPanel Layout
    // EFFECTS: initializes the keyboard panel padding and styling
    public KeyboardPanel() {
        setLayout(new GridBagLayout());

        Insets zeroInset = new Insets(0, 0, 0, 0);
        Font monospace = new Font(Font.MONOSPACED, Font.PLAIN, 12);

        JPanel panelRow;
        JButton b;

        GridBagConstraints keyboardRow = new GridBagConstraints();
        GridBagConstraints keyboardButton = new GridBagConstraints();
        keyboardRow.anchor = GridBagConstraints.WEST;
        keyboardButton.ipady = 14;

        initKeyboard(zeroInset, monospace, keyboardRow, keyboardButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes the keyboard in rows to replicate a QWERTY keyboard
    private void initKeyboard(Insets zeroInset, Font monospace, GridBagConstraints keyboardRow,
                              GridBagConstraints keyboardButton) {
        JPanel panelRow;
        JButton b;
        // first dimension of the key array
        // representing a row on the keyboard
        for (int row = 0, i = 0; row < key.length; ++row) {
            panelRow = new JPanel(new GridBagLayout());

            keyboardRow.gridy = row;

            // second dimension representing each key
            for (int col = 0; col < key[row].length; ++col, ++i) {

                keyboardStyling(zeroInset, keyboardButton, row, col);

                b = new JButton(key[row][col]);
                b.setFont(monospace);
                b.setFocusable(false);
                panelRow.add(b, keyboardButton);
            }

            add(panelRow, keyboardRow);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the styling of the keyboard specific to a few outlier keys
    private void keyboardStyling(Insets zeroInset, GridBagConstraints keyboardButton, int row, int col) {
        // specify padding and insets for the buttons
        switch (key[row][col]) {
            case "Backspace":
                keyboardButton.ipadx = 0;
                break;
            case "Delete":
                keyboardButton.ipadx = 10;
                break;
            case "Enter":
                keyboardButton.ipadx = 27;
                break;
            case "A":
                keyboardButton.insets = new Insets(0, 30, 0, 0);
                break;
            default:
                keyboardButton.ipadx = 3;
                keyboardButton.insets = zeroInset;
        }
    }

    // EFFECTS: run the specific JPanel for the keyboard; appended to Main
    public static void main(String[] args) {
        KeyboardPanel ui = new KeyboardPanel();
    }
}