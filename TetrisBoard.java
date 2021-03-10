/**
 * 
 * 
 * @version 5.00 2018/03/20
 */
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TetrisBoard extends JPanel implements JavaArcade, KeyListener, ActionListener
{
	/** Standard unit of measurement (in pixels) for widths & heights of blocks and columns, etc*/
	public static final int L = 50;
	/** Number of columns on the tetris board */
	public static final int COLUMNS = 10;
	/** Number of rows on the tetris board */
	public static final int ROWS = 15;

	/** Boolean to indicate gameplay */
	private boolean running, paused;
	
	/** Controls how often we update the position of the polyomino */
	private Timer timer;
	/** Current delay of the main timer */
	private int delay;
	/** Initial delay, in millieconds, of the timer */
	private final int DELAY = 1000;
	/** Delay, in milliseconds, of the timer when down arrow is held */
	private final int SHORT_DELAY = 50;
	/** Controls how often the <code>delay</code> decrements (making polyominoes fall faster) */
	private Timer timerTimer;
	/** Delay, in milliseconds, of the timer's speed increases */
	private final int LONG_DELAY = 30000;
	
	/** Player's score */
	private int score;
	private String highScore;
	private PrintWriter writer;
	private Scanner reader;

	/** The actively falling polyomino */
	private Polyomino t;
	/** Contains representations for all the fallen polyomino blocks (represented by <code>Color</code>s) */
	private ArrayList<Color[]> imprints;


	public TetrisBoard(int width, int height)
	{
		this(width, height, Color.LIGHT_GRAY); //new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
	}
	public TetrisBoard(int width, int height, Color color)
	{
		running = false;
		paused = false;
		highScore = "0";
		
		timer = new Timer(DELAY, this);
		timerTimer = new Timer(LONG_DELAY, new TimerTimerListener());

		addKeyListener(this);
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(color);
		
		setPreferredSize(new Dimension(width, height));
		
		//startGame();	// directly start the gameplay
	}
	
	// JAVAARCADE IMPLEMENTATION ///////////////////////////////////////////////////////////////////
	
	public boolean running()
	{
		return running;
	}

	public void startGame()
	{
		if (running)
			return;
		if (paused)
		{
			paused = false;
			timerTimer.start();
		}
		else if (!running)
		{
			score = 0;
			delay = DELAY;
			
			imprints = new ArrayList<Color[]>();
			for (int i = 0; i < ROWS; i++)
				imprints.add(new Color[COLUMNS]);
			
			newPolyomino();		// creates the first piece
			
			repaint();		// paints everything for the first time
			
			timerTimer.restart();
		}
		timer.restart();
		running = true;
	}

	public String getGameName()
	{
		return "UrBoiTetris";
	}

	public void pauseGame()
	{
		timer.stop();
		timerTimer.stop();
		running = false;
		paused = true;
	}

	public String getInstructions()
	{
		return "UP: rotate clockwise\nLEFT/RIGHT: move left/right\nDOWN: speed up downward movement";
	}

	public String getCredits()
	{
		return "Sebastian Harder\nBrendan Nguyen";
	}

	public String getHighScore()
	{
		try
		{
			writer = new PrintWriter(new FileWriter("UltimateTetris/highScores.txt", true));
			reader = new Scanner(new File("UltimateTetris/highScores.txt"));
		} catch (Exception e)
		{
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		while (reader.hasNextLine())
			highScore = reader.nextLine();
		
		if (Integer.parseInt(highScore) < score)
		{
			writer.println();
			writer.println(score);
		}
		
		reader.close();
		writer.close();
		
		return highScore;
	}

	public void stopGame()
	{
		timer.stop();
		timerTimer.stop();
		running = false;
		paused = false;
	}

	public int getPoints()
	{
		return score;
	}
	
	// LISTENER IMPLEMENTATION /////////////////////////////////////////////////////////////////////
	
	/** Action that happens every time <code>timer</code> fires */
	public void actionPerformed(ActionEvent e)
	{
		moveDown();
	}

	/**
	 * Invoked by <code>timerTimer</code>.<br />
	 * Need to create this private class to implement another ActionListener
	 */
	private class TimerTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			delay *= .75;	// 3/4 less delay (4/3 faster)
			timer.setDelay(delay);
			timerTimer.setDelay((int)(timerTimer.getDelay()*1.5));	// longer time until next speed-up
		}
	}
	
	/** Used for keeping track of down arrow */
	private boolean firstPress = true;
	
	public void keyPressed(KeyEvent e)
	{
		if (running)
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:	// rotate polyomino
				rotate();
				break;
			case KeyEvent.VK_DOWN:	// speed up polyomino
				if (firstPress)
				{
					moveDown();
					firstPress = false;
				}
				timer.setDelay(SHORT_DELAY);
				break;
			case KeyEvent.VK_LEFT:	// move polyomino left
				moveLeft();
				break;
			case KeyEvent.VK_RIGHT:	// move polyomino right
				moveRight();
				break;
			case KeyEvent.VK_SPACE:	// pause game
				pauseGame();
				break;
			case KeyEvent.VK_ESCAPE: // exit entire program
				System.exit(0);
				break;
			default:
			}
		else
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:	// start game
				startGame();
				break;
			case KeyEvent.VK_ESCAPE: // exit entire program
				System.exit(0);
				break;
			default:
			}
	}

	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_DOWN:	// stop speeding up polyomino
			timer.setDelay(delay);
			firstPress = true;
			break;
		default:
		}
	}

	public void keyTyped(KeyEvent e) {/*do nothing*/}

	// MOVEMENT METHODS ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Rotates the active polyomino (clockwise) using the Polyomino's method, but also incorporating
	 * a call to repaint() and a check if the rotated version is cut off the screen or not.
	 */
	public void rotate()
	{
		int xInitial = t.getX(), yInitial = t.getY();
		
		t.rotateCW();
		boolean ableToFix = fixRotation();
		if (ableToFix)
		{
			// Check if overlapping old block(s)
			if (!checkValidPosition())
			{
				t.rotateCCW();
				t.setX(xInitial);
				t.setY(yInitial);
			}
		}
		else	// if can't fit on bottom, un-rotate to original
			t.rotateCCW();
		
		repaint();
	}
	
	/**
	 * If the rotated polyomino is off the board, move it to be fully on the board
	 * @return <code>true</code> if all is taken care of<br /><code>false</code> if invalid rotation (rotation must be undone outside of this function)
	 */
	private boolean fixRotation()
	{
		// LEFT SIDE
		int minimum = t.getX() + t.getShape()[0].getX();	// x of first block (can be any block to start)
		for (Block b : t.getShape())
		{
			int x = t.getX() + b.getX();
			if(x < minimum)
				minimum = x;
		}
		for (int i = 0; i > minimum; i--)
			t.moveRight();

		// RIGHT SIDE
		int maximum = minimum;	// x of block w/minimum x (can be x of any block to start)
		for (Block b : t.getShape())
		{
			int x = t.getX() + b.getX();
			if(x > maximum)
				maximum = x;
		}
		for (int i = 0; i < maximum - (COLUMNS-1); i++)
			t.moveLeft();

		// BOTTOM SIDE
		int maximumY = t.getY() + t.getShape()[0].getY();	// y of first block (can be any block to start)
		for(Block b : t.getShape())
		{
			int y = t.getY() + b.getY();
			if(y > maximumY)
				maximumY = y;
		}
		if (maximumY > ROWS-1)
			return false;
		
		return true;
	}
	
	/**
	 * Moves the active polyomino left after checking whether there is
	 * space to the left.
	 */ 
	public void moveLeft()
	{
		t.moveLeft();
		
		if (!checkValidPosition())
			t.moveRight();
		
		repaint();
	}

	/**
	 * Moves the active polyomino right after checking whether there is
	 * space to the right.
	 */
	public void moveRight()
	{
		t.moveRight();
		
		if (!checkValidPosition())
			t.moveLeft();
		
		repaint();
	}

	/**
	 * Checks whether the active polyomino still fits on the board (isn't touching an old polyomino or the top of the board);<br />
	 * if it is touching an old polyomino, adds a new polyomino to the polyominoes list and puts it at the top of the board.;<br />
	 * if at the top, ends the game w/<code>stopGame()</code>.
	 */
	public void moveDown()
	{
		t.moveDown();
		
		if (!checkValidPosition())
		{
			t.moveUp();
			imprint();
			removeRows();
			newPolyomino();
		}
		
		repaint();
	}
	
	/**
	 * Checks if the new position of the polyomino is overlapping any old fallen blocks.
	 * @return <code>true</code> if valid position (or if things have been taken care of)<br /><code>false</code> if movement must be undone
	 */
	private boolean checkValidPosition()
	{
		for (Block b : t.getShape())
		{
			int x = b.getX() + t.getX();
			int y = b.getY() + t.getY();
			
			if (x < 0 || x >= COLUMNS || y >= ROWS)	// if block(s) out of bounds (excluding top)
				return false;	
			if (y < 0)	// skip block if above top of board
				continue;
			
			Color space = imprints.get(y)[x];
			if (space != null)
				return false;
		}
		return true;
	}
	
	/**
	 * "Imprint" the active polyomino onto the board when it can no longer move down
	 */
	private void imprint()
	{
		for (Block b : t.getShape())
		{
			int x = b.getX() + t.getX();
			int y = b.getY() + t.getY();
			
			if (y >= 0 && y < ROWS && x >= 0 && x < COLUMNS)
				imprints.get(y)[x] = t.getColor();
		}
	}
	
	private void removeRows()
	{
		for (int i = ROWS-1; i >= 0; i--)
		{
			Color[] row = imprints.get(i);
			int filledSpaces = 0;
			for (int j = 0; j < row.length; j++)
			{
				if (row[j] != null)
				{
					//System.out.print(1);
					filledSpaces++;
				}
				/*else
					System.out.print(0);*/
			}
			//System.out.println(" " + filledSpaces);
			if (filledSpaces == COLUMNS)
			{
				score += COLUMNS;
				imprints.remove(i);
				imprints.add(0, new Color[COLUMNS]);
				i++;
			}
		}
	}
	
	/**
	 * Creates a new active <code>Polyomino</code>, makes it the first <code>Polyomino</code> in <code>polyominoes</code> array
	 * and pushes back all the old <code>Polyomino</code>s' indexes.<br />
	 * <b>Note:</b> Does not call <code>repaint()</code> (prevents redundancy where this function is called)
	 */
	private Polyomino newPolyomino()
	{
		int i = (int)(Math.random()*50) + 1;
		if (i < 3)
			t = new Monomino(COLUMNS/2 - 1, 0);
		else if (i < 3+6)
			t = new Domino(COLUMNS/2 - 1, 0);
		else if (i < 3+6+13)
			t = new Triomino(COLUMNS/2 - 2, -1);
		else if (i < 3+6+13+14)
			t = new Tetromino(COLUMNS/2 - 2, -2);
		else if (i < 3+6+13+14+13)
			t = new Pentomino(COLUMNS/2 - 3, -3);
		else
			t = new Mysteryomino(COLUMNS/2 - 4, -4);
		
		/*switch(i) {
			case 1:
				t = new Monomino(COLUMNS/2 - 1, 0);
				break;
			case 2:
				t = new Domino(COLUMNS/2 - 1, 0);
				break;
			case 3:
				t = new Triomino(COLUMNS/2 - 2, -1);
				break;
			case 4:
				t = new Tetromino(COLUMNS/2 - 2, -2);
				break;
			case 5:
				t = new Pentomino(COLUMNS/2 - 3, -3);
				break;
			default:
		}*/
		
		if (!checkValidPosition())
			endGame();
		
		return t;
	}
	
	private void endGame()
	{
		t = null;
		this.stopGame();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
		
	/** Draws everything */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// STATS
		if (t != null)
			g.drawString("Currently falling: \"" + t.getName() + "\" " + t.getPolyomino(), 30, 30);
		//else
			//g.drawString("No polyomino currently falling", 120, 30);
		if (timer.isRunning())
			g.drawString("Current speed: " + delay + " ms/step", 30, 50);
		//g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 30, 70);
		
		
		
		// DRAW ACTIVE POLYOMINO
		if (t != null)
		{
			g.setColor(t.getColor());
			for (Block b : t.getShape())
			{
				g.fillRect(L*(t.getX() + b.getX()), L*(t.getY() + b.getY()), L, L);
			}
		}
		
		// DRAW OLD POLYOMINO BLOCKS
		if (imprints != null)
		{
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLUMNS; j++)
				{
					if (imprints.get(i)[j] == null)
						continue;
					g.setColor(imprints.get(i)[j]);
					g.fillRect(L*j, L*i, L, L);
				}
		}	
	}
	

}
