/**
 * A pentomino object to be used in Tetris. Composed of 5 blocks.
 * 
 * @author Sebastian H
 * @version 1.00 2018/03/20
 */
 

import java.awt.Color;
import java.awt.Graphics;

public class Pentomino extends Polyomino
{

	/** Final ints representing names of pentominoes */
	public final int F = 0, _7 = 1, I = 2, L = 3, J = 4, N = 5, S = 6, P = 7, Q = 8,
			T = 9, U = 10, V = 11, W = 12, X = 13, Y = 14, _L = 15, Z = 16, _S = 17;

	/**
	 * Constructs a new random pentomino.<br />
	 * Sets color and starting orientation randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.
	 */
	public Pentomino(int x, int y)
	{
		this(x, y, (int)(Math.random()*18), (int)(Math.random()*4));
	}
	/**
	 * Constructs a new pentomino, based on the type.<br />
	 * Sets color randomly, and creates an array of orientations, each composed of 4 <code>Block</code>s.<br />
	 * <b>Starts in whichever orientation is stated in the parameter. If out of range 0-3, defaults to 0 (UP).</b> 
	 */
	public Pentomino(int x, int y, int type, int orientation)
	{
		// set x and y for the shape
		setX(x);
		setY(y);

		// set color for the shape
		setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
		
		// set pentomino type & orientations
		setType(type);
		if (type == F)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(3,1), new Block(1,2), new Block(2,2), new Block(2,3)},
				{new Block(1,2), new Block(1,1), new Block(2,3), new Block(2,2), new Block(3,2)},
				{new Block(2,3), new Block(1,3), new Block(3,2), new Block(2,2), new Block(2,1)},
				{new Block(3,2), new Block(3,3), new Block(2,1), new Block(2,2), new Block(1,2)}});
		}
		else if (type == _7)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(2,1), new Block(2,2), new Block(3,2), new Block(2,3)},
				{new Block(1,3), new Block(1,2), new Block(2,2), new Block(2,1), new Block(3,2)},
				{new Block(3,3), new Block(2,3), new Block(2,2), new Block(1,2), new Block(2,1)},
				{new Block(3,1), new Block(3,2), new Block(2,2), new Block(2,3), new Block(1,2)}});
		}
		else if (type == I)
		{
			setOrientations(new Block[][] {
				{new Block(0,2), new Block(1,2), new Block(2,2), new Block(3,2), new Block(4,2)},
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(2,3), new Block(2,4)},
				{new Block(0,2), new Block(1,2), new Block(2,2), new Block(3,2), new Block(4,2)},
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(2,3), new Block(2,4)}});
		}
		else if (type == L)
		{
			setOrientations(new Block[][] {
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(2,3), new Block(3,3)},
				{new Block(0,2), new Block(1,2), new Block(2,2), new Block(3,2), new Block(3,1)},
				{new Block(2,4), new Block(2,3), new Block(2,2), new Block(2,1), new Block(1,1)},
				{new Block(4,2), new Block(3,2), new Block(2,2), new Block(1,2), new Block(1,3)}});
		}
		else if (type == J)
		{
			setOrientations(new Block[][] {
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(1,3), new Block(2,3)},
				{new Block(0,2), new Block(1,2), new Block(2,2), new Block(3,3), new Block(3,2)},
				{new Block(2,4), new Block(2,3), new Block(2,2), new Block(3,1), new Block(2,1)},
				{new Block(4,2), new Block(3,2), new Block(2,2), new Block(1,1), new Block(1,2)}});
		}
		else if (type == N)
		{
			setOrientations(new Block[][] {
				{new Block(2,0), new Block(2,1), new Block(1,2), new Block(2,2), new Block(1,3)},
				{new Block(0,2), new Block(1,2), new Block(2,3), new Block(2,2), new Block(3,3)},
				{new Block(2,4), new Block(2,3), new Block(3,2), new Block(2,2), new Block(3,1)},
				{new Block(4,2), new Block(3,2), new Block(2,1), new Block(2,2), new Block(1,1)}});
		}
		else if (type == S)
		{
			setOrientations(new Block[][] {
				{new Block(2,0), new Block(2,1), new Block(2,2), new Block(3,2), new Block(3,3)},
				{new Block(0,2), new Block(1,2), new Block(2,2), new Block(2,1), new Block(3,1)},
				{new Block(2,4), new Block(2,3), new Block(2,2), new Block(1,2), new Block(1,1)},
				{new Block(4,2), new Block(3,2), new Block(2,2), new Block(2,3), new Block(1,3)}});
		}
		else if (type == P)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(3,1), new Block(2,2), new Block(3,2), new Block(2,3)},
				{new Block(1,2), new Block(1,1), new Block(2,2), new Block(2,1), new Block(3,2)},
				{new Block(2,3), new Block(1,3), new Block(2,2), new Block(1,2), new Block(2,1)},
				{new Block(3,2), new Block(3,3), new Block(2,2), new Block(2,3), new Block(1,2)}});
		}
		else if (type == Q)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(1,1), new Block(2,2), new Block(1,2), new Block(2,3)},
				{new Block(1,2), new Block(1,3), new Block(2,2), new Block(2,3), new Block(3,2)},
				{new Block(2,3), new Block(3,3), new Block(2,2), new Block(3,2), new Block(2,1)},
				{new Block(3,2), new Block(3,1), new Block(2,2), new Block(2,1), new Block(1,2)}});
		}
		else if (type == T)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(2,1), new Block(3,1), new Block(2,2), new Block(2,3)},
				{new Block(1,3), new Block(1,2), new Block(1,1), new Block(2,2), new Block(3,2)},
				{new Block(3,3), new Block(2,3), new Block(1,3), new Block(2,2), new Block(2,1)},
				{new Block(3,1), new Block(3,2), new Block(3,3), new Block(2,2), new Block(1,2)}});
		}
		else if (type == U)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(3,1), new Block(1,2), new Block(2,2), new Block(3,2)},
				{new Block(1,3), new Block(1,1), new Block(2,3), new Block(2,2), new Block(2,1)},
				{new Block(3,3), new Block(1,3), new Block(3,2), new Block(2,2), new Block(1,2)},
				{new Block(3,1), new Block(3,3), new Block(2,1), new Block(2,2), new Block(2,3)}});
		}
		else if (type == V)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(1,2), new Block(1,3), new Block(2,3), new Block(3,3)},
				{new Block(1,3), new Block(2,3), new Block(3,3), new Block(3,2), new Block(3,1)},
				{new Block(3,3), new Block(3,2), new Block(3,1), new Block(2,1), new Block(1,1)},
				{new Block(3,1), new Block(2,1), new Block(1,1), new Block(1,2), new Block(1,3)}});
		}
		else if (type == W)
		{
			setOrientations(new Block[][] {
				{new Block(3,1), new Block(2,2), new Block(3,2), new Block(1,3), new Block(2,3)},
				{new Block(1,1), new Block(2,2), new Block(2,1), new Block(3,3), new Block(3,2)},
				{new Block(1,3), new Block(2,2), new Block(1,2), new Block(3,1), new Block(2,1)},
				{new Block(3,3), new Block(2,2), new Block(2,3), new Block(1,1), new Block(1,2)}});
		}
		else if (type == X)
		{
			// all orientations are the same (symmetry all around)
			Block[] orientation_X = new Block[] {new Block(2,1), new Block(1,2), new Block(2,2), new Block(3,2), new Block(2,3)};
			setOrientations(new Block[][] {orientation_X, orientation_X, orientation_X, orientation_X});
		}
		else if (type == Y)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(0,2), new Block(1,2), new Block(2,2), new Block(3,2)},
				{new Block(1,2), new Block(2,4), new Block(2,3), new Block(2,2), new Block(2,1)},
				{new Block(2,3), new Block(4,2), new Block(3,2), new Block(2,2), new Block(1,2)},
				{new Block(3,2), new Block(2,0), new Block(2,1), new Block(2,2), new Block(2,3)}});
		}
		else if (type == _L)
		{
			setOrientations(new Block[][] {
				{new Block(2,1), new Block(4,2), new Block(1,2), new Block(2,2), new Block(3,2)},
				{new Block(1,2), new Block(2,0), new Block(2,3), new Block(2,2), new Block(2,1)},
				{new Block(2,3), new Block(0,2), new Block(3,2), new Block(2,2), new Block(1,2)},
				{new Block(3,2), new Block(2,4), new Block(2,1), new Block(2,2), new Block(2,3)}});
		}
		else if (type == Z)
		{
			setOrientations(new Block[][] {
				{new Block(1,1), new Block(2,1), new Block(2,2), new Block(2,3), new Block(3,3)},
				{new Block(1,3), new Block(1,2), new Block(2,2), new Block(3,2), new Block(3,1)},
				{new Block(3,3), new Block(2,3), new Block(2,2), new Block(2,1), new Block(1,1)},
				{new Block(3,1), new Block(3,2), new Block(2,2), new Block(1,2), new Block(1,3)}});
		}
		else if (type == _S)
		{
			setOrientations(new Block[][] {
				{new Block(3,1), new Block(2,1), new Block(2,2), new Block(2,3), new Block(1,3)},
				{new Block(1,1), new Block(1,2), new Block(2,2), new Block(3,2), new Block(3,3)},
				{new Block(1,3), new Block(2,3), new Block(2,2), new Block(2,1), new Block(3,1)},
				{new Block(3,3), new Block(3,2), new Block(2,2), new Block(1,2), new Block(1,1)}});
		}
		
		
		
		// set starting orientation
		if (orientation >= 0 && orientation < 4)
			setOrientation(orientation);
		else
			setOrientation(UP);
		
	}
	
	public String getName()
	{
		if (getType() == F)
			return "F";
		if (getType() == _7)
			return "7";
		if (getType() == I)
			return "I";
		if (getType() == L)
			return "L";
		if (getType() == J)
			return "J";
		if (getType() == N)
			return "N";
		if (getType() == S)
			return "S";
		if (getType() == P)
			return "P";
		if (getType() == Q)
			return "Q";
		if (getType() == T)
			return "T";
		if (getType() == U)
			return "U";
		if (getType() == V)
			return "V";
		if (getType() == W)
			return "W";
		if (getType() == X)
			return "X";
		if (getType() == Y)
			return "Y";
		if (getType() == _L)
			return "lambda";
		if (getType() == Z)
			return "Z";
		if (getType() == _S)
			return "integral";
		return "none";
	}
	
	public String getPolyomino()
	{
		return "pentomino";
	}
	
	public String toString()
	{
		String ret = "Block coordinates of \"";
		ret += getName();
		ret += "\" pentomino (currently oriented ";
		if (getOrientation() == UP)
			ret += "upright";
		else if (getOrientation() == LEFT)
			ret += "on its left";
		else if (getOrientation() == DOWN)
			ret += "upside down";
		else if (getOrientation() == RIGHT)
			ret += "on its right";
		ret += "):\n";
		Block[] pentomino = getShape();
		for (int i = 0; i < pentomino.length; i++)
		{
			ret += "(" + (getX() + pentomino[i].getX()) + ", " + (getY() + pentomino[i].getY()) + ")";
			if (i < pentomino.length - 1)
				ret += ", ";
		}
		return ret;
	}
}