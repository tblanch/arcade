/**
 * A domino object to be used in Tetris. Composed of 4 blocks.
 * 
 * @version 1.00 2018/03/20
 */
 

import java.awt.Color;
import java.awt.Graphics;

public class Domino extends Polyomino
{

	/** Final ints representing names of dominoes */
	public final int domino = 0;

	/**
	 * Constructs a new random domino.<br />
	 * Sets color and starting orientation randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.
	 */
	public Domino(int x, int y)
	{
		this(x, y, (int)(Math.random()*4));
	}
	/**
	 * Constructs a new domino, based on the type.<br />
	 * Sets color randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.<br />
	 * <b>Starts in whichever orientation is stated in the parameter. If out of range 0-3, defaults to 0 (UP).</b> 
	 */
	public Domino(int x, int y, int orientation)
	{
		// set x and y for the shape
		setX(x);
		setY(y);

		// set color for the shape
		setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		
		// set domino type & orientations
		setType(domino);
		setOrientations(new Block[][] {
			{new Block(0,0), new Block(1,0)},
			{new Block(0,0), new Block(0,1)},
			{new Block(0,1), new Block(1,1)},
			{new Block(1,0), new Block(1,1)}});
		
		// set starting orientation
		if (orientation >= 0 && orientation < 4)
			setOrientation(orientation);
		else
			setOrientation(UP);
		
	}
	
	public String getName()
	{
		return getPolyomino();
	}
	
	public String getPolyomino()
	{
		return "domino";
	}
	
	public String toString()
	{
		String ret = "Block coordinates of ";
		//ret += getName();
		ret += "domino (currently oriented ";
		if (getOrientation() == UP)
			ret += "upright";
		else if (getOrientation() == LEFT)
			ret += "on its left";
		else if (getOrientation() == DOWN)
			ret += "upside down";
		else if (getOrientation() == RIGHT)
			ret += "on its right";
		ret += "):\n";
		Block[] domino = getShape();
		for (int i = 0; i < domino.length; i++)
		{
			ret += "(" + (getX() + domino[i].getX()) + ", " + (getY() + domino[i].getY()) + ")";
			if (i < domino.length - 1)
				ret += ", ";
		}
		return ret;
	}
	
}
