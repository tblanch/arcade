/**
 * Simple coordinates (unaffliated w/GUI positioning), used for positions on a 4x4 grid.
 * x is horizontal, y is vertical (downward +y for consistency w/GUI use)
 *
 * @author Sebastian H
 * @version 1.00 2018/03/10
 */
 

public class Block
{

	/** Position of the top left corner of the <code>Block</code> on the <code>Tetromino</code> canvas */
	private int x, y;

	public Block(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/** Position of the top left corner of the <code>Block</code> on the <code>Tetromino</code> canvas */
	public int getX()
	{
		return x;
	}
	/** Position of the top left corner of the <code>Block</code> on the <code>Tetromino</code> canvas */
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	/*public void changeX(int deltaX)
	{
		x += deltaX;
	}
	public void changeY(int deltaY)
	{
		y += deltaY;
	}*/
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

}