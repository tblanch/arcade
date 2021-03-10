// Represents a control panel for the arcade
 

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.*;

public class ControlPanel extends JPanel
implements ActionListener
{
	private JavaArcade game;
	private GameStats gStats;
	private JButton startButton, pauseButton, stopButton, instructionsButton, creditsButton;

	// Constructor
	public ControlPanel(JavaArcade t, GameStats g)
	{
		game = t;
		gStats = g;

		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		add(instructionsButton);
		
		creditsButton = new JButton("Credits");
		creditsButton.addActionListener(this);
		add(creditsButton);
		
		add(Box.createHorizontalStrut(80)); 
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		add(startButton);
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		add(pauseButton);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(this);
		add(stopButton);


	}

	// Called when the start button is clicked
	public void actionPerformed(ActionEvent e)
	{

		JButton button = (JButton)e.getSource();

		if (button == startButton)
		{
			if (!game.running()) 
			{
				((JPanel)(game)).requestFocus(); //Required so that panel will respond
				game.startGame();
				pauseButton.setEnabled(true);
				stopButton.setEnabled(true);
				startButton.setText("Start");
				startButton.setEnabled(true);
			}
		}
		else if(button == pauseButton)
		{
			game.pauseGame();
			startButton.setText("Resume");
			startButton.setEnabled(true);
			pauseButton.setEnabled(true);
			repaint();
		}
		else if(button == stopButton)
		{
			game.stopGame();
			gStats.update(game.getPoints());
			gStats.repaint();
			startButton.setText("Restart");
			startButton.setEnabled(true);
			stopButton.setEnabled(true);
			pauseButton.setEnabled(true);
			repaint();
		}  
		else if(button == creditsButton)
		{
			String credits = game.getCredits();
			JOptionPane.showMessageDialog(this, credits, "Game Credits", JOptionPane.PLAIN_MESSAGE);

		}      
		else if(button == instructionsButton)
		{
			String instructions = game.getInstructions();
			JOptionPane.showMessageDialog(this, instructions, "Game Rules", JOptionPane.PLAIN_MESSAGE);

		}
		((JPanel)(game)).requestFocus();      
	}

}
