// Represents a display panel the arcade
 

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GameStats extends JPanel
{
	private JTextField gameNameText, currentHighScorer, currentHighScore;
	private int yourScore;
	private JLabel yourScoreText;
	private JavaArcade game;

	// Constructor
	public GameStats(JavaArcade t)
	{
		super(new GridLayout(2, 4, 10, 0));
		setBorder(new EmptyBorder(0, 0, 5, 0));
		Font gameNameFont = new Font("Monospaced", Font.BOLD, 24);
		game = t;
		JLabel gName = new JLabel(" " + t.getGameName());
		Font gameNoteFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		JLabel gNote = new JLabel("   Close window to return to menu");

		gName.setForeground(Color.red);
		gName.setFont(gameNameFont);
		add(gName);
		gNote.setForeground(Color.blue);
		gNote.setFont(gameNoteFont);
		add(gNote);
		
		add(new JLabel(" Current High Score:   " + t.getHighScore()));

		yourScoreText = new JLabel(" Your Final Score: " + 0);

		add(yourScoreText);
		Font displayFont = new Font("Monospaced", Font.BOLD, 16);


	}

	// Updates this display at end of game
	public void update(int points)
	{

		yourScoreText.setText(" Your Final Score: " + points);
		if(points > Integer.parseInt(game.getHighScore())){
			yourScoreText.setForeground(Color.BLUE);
			String s = (String)JOptionPane.showInputDialog(this, "You are the new high scorer. Congratulations!\n Enter your name: ", "High Score", JOptionPane.PLAIN_MESSAGE, null, null,"name");
			JOptionPane.showMessageDialog(null, "You are the new high scorer!");}
		else
			JOptionPane.showMessageDialog(null, "You did not beat the high score");

	}
}