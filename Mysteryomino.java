/**
 * A mysteromino object to be used in Tetris. Composed of 1 block.
 * 
 * @version 1.00 2018/03/20
 */
 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

public class Mysteryomino extends Polyomino implements ActionListener
{

	private Timer timer;
	
	/** Final ints representing names of mysterominoes */
	public final int smiley = 0;

	/**
	 * Constructs a new random mysteromino.<br />
	 * Sets color and starting orientation randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.
	 */
	public Mysteryomino(int x, int y)
	{
		this(x, y,(int)(Math.random()*4));
	}
	/**
	 * Constructs a new mysteromino, based on the type.<br />
	 * Sets color randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.<br />
	 * <b>Starts in whichever orientation is stated in the parameter. If out of range 0-3, defaults to 0 (UP).</b> 
	 */
	public Mysteryomino(int x, int y, int orientation)
	{
		timer = new Timer(1000, this);
		timer.start();
		
		// set x and y for the shape
		setX(x);
		setY(y);

		// set color for the shape
		setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		
		// set mysteromino type & orientations
		setType(smiley);
		
		setOrientations(new Block[][] {
			{new Block(1,1), new Block(3,1), new Block(1,3), new Block(3,3), new Block(1,4), new Block(2,4), new Block(3,4)},
			{new Block(1,3), new Block(1,1), new Block(3,3), new Block(3,1), new Block(4,3), new Block(4,2), new Block(4,1)},
			{new Block(3,3), new Block(1,3), new Block(3,1), new Block(1,1), new Block(3,0), new Block(2,0), new Block(1,0)},
			{new Block(3,1), new Block(3,3), new Block(1,1), new Block(1,3), new Block(0,1), new Block(0,2), new Block(0,3)}});
		
		// set starting orientation
		if (orientation >= 0 && orientation < 4)
			setOrientation(orientation);
		else
			setOrientation(UP);
		
	}
	
	public String getName()
	{
		return "smiley";
	}
	
	public String getPolyomino()
	{
		return "mysteryomino";
	}
	
	public String toString()
	{
		String ret = "Block coordinates of ";
		//ret += getName();
		ret += "mysteryomino (currently oriented ";
		if (getOrientation() == UP)
			ret += "upright";
		else if (getOrientation() == LEFT)
			ret += "on its left";
		else if (getOrientation() == DOWN)
			ret += "upside down";
		else if (getOrientation() == RIGHT)
			ret += "on its right";
		ret += "):\n";
		Block[] mysteryomino = getShape();
		for (int i = 0; i < mysteryomino.length; i++)
		{
			ret += "(" + (getX() + mysteryomino[i].getX()) + ", " + (getY() + mysteryomino[i].getY()) + ")";
			if (i < mysteryomino.length - 1)
				ret += ", ";
		}
		return ret;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
	}
	
}
