/**
 * @(#)Pong.java
 *
 *
 * @author 	Tucker Blanch
 * @version 1.00 2017/6/19
 */
 

import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;

public class Pong extends JFrame {
    private final static int WIDTH = 800, HEIGHT = 800; // sets the size of the GUI panel
    private PongPanel panel;

    public Pong() {
        setSize(WIDTH, HEIGHT);
        setTitle("Ultimate Pong");
        setBackground(Color.BLACK);
        setResizable(false);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new PongPanel(this);
        Container c = getContentPane();
        c.add(panel);
        //add(panel);
         setVisible(true);
    }

    public PongPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new Pong();
    }
}