package ui;

import javax.swing.*;
import java.awt.event.*;

// Creates a help button that users can click; for viewing instructions.
public class HelpButton extends JButton {
    // SOURCE: https://stackoverflow.com/questions/2275277/how-to-put-clickable-image-jframe
//    public void main(String[] args) {
//        // Create a "clickable" image icon.
//        ImageIcon icon = new ImageIcon("path/to/image.jpg");
//        JLabel label = new JLabel("Help");
//
//        this.setText("Help");
//
//        label.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent me) {
//                System.out.println("CLICKED");
//            }
//        });
//
//        // Add it to a frame.
//        JFrame frame = new JFrame("My Window");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(label);
//        frame.pack();
//        frame.setVisible(true);
//    }

    public HelpButton() {
        this.setText("Help");
        // initialize
    }
}