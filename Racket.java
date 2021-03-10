/**
 * @(#)Racket.java
 *
 *
 * @author Tucker Blanch
 * @version 1.00 2017/6/19
 */
 

import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
    private static final int WIDTH = 10, HEIGHT = 60;// sets the size of the racket
    private Pong game;
    private int up, down;
    private int x;
    private int y, ya;

    public Racket(Pong game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
    }

    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29)
            y += ya;
        else if (y == 0)
            y+=2;
        else if (y == game.getHeight() - HEIGHT - 29)
            y-=2;
    }

    public void pressed(int keyCode) { // this adjusts the position depending whick button is pressed
        if (keyCode == up)
            ya = -2;
        else if (keyCode == down)
            ya = 2;
    }

    public void released(int keyCode) {
        if (keyCode == up || keyCode == down)
            ya = 0;
    }

    public Rectangle getBounds() { // this makes sure that the racket doesn't go off the screen
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) { // this paints the racket in
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}