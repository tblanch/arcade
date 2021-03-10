/**
 * @(#)Ball.java
 *
 *
 * @author Tucker Blanch
 * @version 1.00 2017/6/19
 */
 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Ball {
    private static final int WIDTH = 35, HEIGHT = 35; // sets the size of the ball
    private Pong game;
    private int x, y, xa = 2, ya = 1;
    

    public Ball(Pong game) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
    }

    public void update() { // this checks where it hits the wall and it will change the score 
        x += xa;
        y += ya;
        if (x < 0) {
            game.getPanel().increaseScore(1);
            x = game.getWidth() / 2;
            xa = -xa;
        }
        else if (x > game.getWidth() - WIDTH - 7) {
            game.getPanel().increaseScore(2);
            x = game.getWidth() / 2;
            xa = -xa;
        }
        else if (y < 0 || y > game.getHeight() - HEIGHT - 29){
            ya = -ya;
            try{
        		Thread.sleep(10);
        	}
        	catch(Exception e){
        		
        	}
        if (game.getPanel().getScore(1) == 10){
            JOptionPane.showMessageDialog(null, "Player 1 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        else if (game.getPanel().getScore(2) == 10){
            JOptionPane.showMessageDialog(null, "Player 2 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        }
        checkCollision();
    }

    public void checkCollision() {// checks if the ball hits 
        if (game.getPanel().getPlayer(1).getBounds().intersects(getBounds()) || game.getPanel().getPlayer(2).getBounds().intersects(getBounds()))
            xa = -xa; // this shows that when it hits a racket it changes direction in the x-axis

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}