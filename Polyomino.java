/**
 * A polyomino object to be used in Tetris+. Concrete children are composed of n blocks.
 * 
 * @author Sebastian H and Brendan N
 * @version 1.00 2018/03/20
 */
 

import java.awt.Color;
import java.awt.Graphics;

public abstract class Polyomino
{

	/** Position of the top left corner of the <code>Polyomino</code> canvas on the <code>TetrisBoard</code><br />
	 * (in TetrisBoard.L units, not pixels) */
	private int x, y;

	/**
	 * Integer representing current orientation of the polyomino<br/>
	 * (UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3)
	 */
	private int currentOrientation;
	public final int UP = 0;
	public final int LEFT = 1;
	public final int DOWN = 2;
	public final int RIGHT = 3;

	/**
	 * 2D <code>Block</code> array (4*n) that holds the 4 orientations (UP, LEFT, DOWN, RIGHT) for this tetromino.
	 * "Orientations" themselves are the polyomino <code>Block</code>s put together.
	 */
	private Block[][] allOrientations;

	/** Integer representing the type of polyomino */
	private int type;

	private Color color;


	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void moveLeft()
	{
		x--;
	}
	public void moveRight()
	{
		x++;
	}
	public void moveUp()
	{
		y--;
	}
	public void moveDown()
	{
		y++;
	}

	/** Rotates the tetromino counterclockwise (left).<br />Changes <code>orientation</code> accordingly. If orientation increases past RIGHT (3), reset to UP (0). */
	public void rotateCCW()
	{
		if (currentOrientation >= 3)
			currentOrientation = UP;
		else
			currentOrientation--;
	}
	/** Rotates the tetromino clockwise (right).<br />Changes <code>orientation</code> accordingly. If orientation decreases past UP (0), reset to RIGHT (3). */
	public void rotateCW()
	{
		if (currentOrientation <= 0)
			currentOrientation = RIGHT;
		else
			currentOrientation--;
	}

	/** Position of the top left corner of the <code>Tetromino</code> canvas on the <code>TetrisBoard</code> */
	public int getX()
	{
		return x;
	}
	/** Position of the top left corner of the <code>Tetromino</code> canvas on the <code>TetrisBoard</code> */
	public int getY()
	{
		return y;
	}
	/**
	 * Integer representing current orientation of the tetromino<br/>
	 * (UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3)
	 */
	public int getOrientation()
	{
		return currentOrientation;
	}
	
	public void setOrientation(int n) {
		currentOrientation = n;
	}
	/**
	 * Array of <code>Block</code>s
	 * in the current orientation of this <code>Tetromino</code>.<br />
	 * (4 items in the array)
	 */
	public Block[] getShape()
	{
		return allOrientations[currentOrientation];
	}
	/**
	 * 2D <code>Block</code> array (4x4) that holds the 4 orientations (UP, LEFT, DOWN, RIGHT) for this tetromino.
	 * "Orientations" themselves are 4 tetromino <code>Block</code>s put together.
	 */
	public Block[][] getOrientations()
	{
		return allOrientations;
	}
	
	public void setOrientations(Block[][] o) {
		allOrientations = o;
	}
	
	/**
	 * String w/the name of the specific polyomino shape
	 */
	public abstract String getName();
	
	/**
	 * String w/the type of polyomino (domino, pentomino, etc)
	 */
	public abstract String getPolyomino();
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setType(int n) {
		type = n;
	}
	
	public int getType() {
		return type;
	}

	public abstract String toString();


	public static void main(String[] args)
	{
		Polyomino[] minos = new Polyomino[] {
				new Monomino(4,9),
				new Domino(7,8),
				new Triomino(2,0),
				new Tetromino(1,5),
				new Pentomino(3,6)};

		for (Polyomino p : minos)
			System.out.println(p);

	}

}