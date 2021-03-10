/**
 * A tetromino object to be used in Tetris. Composed of 4 blocks.
 * 
 * @author Sebastian H and Brendan N
 * @version 2.00 2018/03/20
 */
 

import java.awt.Color;
import java.awt.Graphics;

public class Tetromino extends Polyomino
{

	/** Final ints representing names of tetrominoes */
	public final int I = 0,  J = 1, L = 2, O = 3, S = 4, T = 5, Z = 6;

	/**
	 * Constructs a new random tetromino.<br />
	 * Sets color and starting orientation randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.
	 */
	public Tetromino(int x, int y)
	{
		this(x, y, (int)(Math.random()*7), (int)(Math.random()*4));
	}
	/**
	 * Constructs a new tetromino, based on the type.<br />
	 * Sets color randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.<br />
	 * <b>Starts in whichever orientation is stated in the parameter. If out of range 0-3, defaults to 0 (UP).</b> 
	 */
	public Tetromino(int x, int y, int type, int orientation)
	{
		// set x and y for the shape
		setX(x);
		setY(y);
		
		// set color for the shape
		setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		
		// set tetromino type & orientations
		setType(type);
		if (type == I)
		{
			setOrientations(new Block[][] {
				{new Block(0,1), new Block(1,1), new Block(2,1), new Block(3,1)},
				{new Block(1,3), new Block(1,2), new Block(1,1), new Block(1,0)},
				{new Block(3,2), new Block(2,2), new Block(1,2), new Block(0,2)},
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(2,3)}});
		}
		else if (type == J)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(0,2), new Block(1,2), new Block(2,2)},
				{new Block(1,1), new Block(2,3), new Block(2,2), new Block(2,1)},
				{new Block(1,2), new Block(3,1), new Block(2,1), new Block(1,1)},
				{new Block(2,2), new Block(1,0), new Block(1,1), new Block(1,2)}});
		}
		else if (type == L)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(1,2), new Block(2,2), new Block(3,2)},
				{new Block(1,2), new Block(2,2), new Block(2,1), new Block(2,0)},
				{new Block(2,2), new Block(2,1), new Block(1,1), new Block(0,1)},
				{new Block(2,1), new Block(1,1), new Block(1,2), new Block(1,3)}});
		}
		else if (type == O)
		{
			// all orientations are the same (symmetry all around)
			Block[] orientation_O = new Block[] {new Block(1,1), new Block(2,1), new Block(1,2), new Block(2,2)};
			setOrientations(new Block[][] {orientation_O, orientation_O, orientation_O, orientation_O});
		}
		else if (type == S)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(2,1), new Block(0,2), new Block(1,2)},
				{new Block(1,2), new Block(1,1), new Block(2,3), new Block(2,2)},
				{new Block(2,2), new Block(1,2), new Block(3,1), new Block(2,1)},
				{new Block(2,1), new Block(2,2), new Block(1,0), new Block(1,1)}});
		}
		else if (type == T)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(0,2), new Block(1,2), new Block(2,2)},
				{new Block(1,2), new Block(2,3), new Block(2,2), new Block(2,1)},
				{new Block(2,2), new Block(3,1), new Block(2,1), new Block(1,1)},
				{new Block(2,1), new Block(1,0), new Block(1,1), new Block(1,2)}});
		}
		else if (type == Z)
		{
			setOrientations(new Block[][] {
				{new Block(0,1), new Block(1,1), new Block(1,2), new Block(2,2)},
				{new Block(1,3), new Block(1,2), new Block(2,2), new Block(2,1)},
				{new Block(3,2), new Block(2,2), new Block(2,1), new Block(1,1)},
				{new Block(2,0), new Block(2,1), new Block(1,1), new Block(1,2)}});
		}
		
		// set starting orientation
		if (orientation >= 0 && orientation < 4)
			setOrientation(orientation);
		else
			setOrientation(UP);
		
	}
	
	public String getName()
	{
		if (getType() == I)
			return "I";
		if (getType() == J)
			return "J";
		if (getType() == L)
			return "L";
		if (getType() == O)
			return "O";
		if (getType() == S)
			return "S";
		if (getType() == T)
			return "T";
		if (getType() == Z)
			return "Z";
		return "none";
	}
	
	public String getPolyomino()
	{
		return "tetromino";
	}
	
	public String toString()
	{
		String ret = "Block coordinates of \"";
		ret += getName();
		ret += "\" tetromino (currently oriented ";
		if (getOrientation() == UP)
			ret += "upright";
		else if (getOrientation() == LEFT)
			ret += "on its left";
		else if (getOrientation() == DOWN)
			ret += "upside down";
		else if (getOrientation() == RIGHT)
			ret += "on its right";
		ret += "):\n";
		Block[] tetromino = getShape();
		for (int i = 0; i < tetromino.length; i++)
		{
			ret += "(" + (getX() + tetromino[i].getX()) + ", " + (getY() + tetromino[i].getY()) + ")";
			if (i < tetromino.length - 1)
				ret += ", ";
		}
		return ret;
	}
}