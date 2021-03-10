 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plane {
    private static final int WIDTH = 20, HEIGHT = 20;// sets the size of the plane
    private int heading;
    private double speed;
    private int x, y;
    private int left, right;
    private Streamer str;
    private Color c;

    public Plane(int x1, int y1, int h, double s, int l, int r, Color c1) {
    	x = x1;
    	y = y1;
    	heading = h;
    	speed = s;
    	left = l;
    	right = r;
    	str = new Streamer(c);
    	c = c1;
    }

    public void update(Plane p) {
    	x -= (int)(speed * Math.cos( (double)heading / 180.0 * Math.PI));
    	y -= (int)(speed * Math.sin((double)heading / 180.0 * Math.PI));
    	str.update(x +10,y+10,p);
     }

    public void pressed(int keyCode) { // adjusts the heading 
        if (keyCode == left)
        	heading-=20;
        else if (keyCode == right)
        	heading+=20;
    }

    public Rectangle getBounds() { // this makes sure that the racket doesn't go off the screen
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public void paint(Graphics g) { // paints the plane (A square for now)
    	g.setColor(c);
        g.fillRect(x, y, WIDTH, HEIGHT);
        str.paint(g);
    }
    
    public int getLength(){
    	return str.getLength();
    }
}