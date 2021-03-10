 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SkyPanel extends JPanel implements ActionListener, KeyListener {
    private CombatSim game;
    private Plane player1, player2;
    private double speed = 4;
    private static double timePassed;

    public SkyPanel(CombatSim g) {
        setBackground(Color.WHITE);
        game = g;
        player1 = new Plane(game.getWidth()/4, 80, 270, speed, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, Color.BLUE);
        player2 = new Plane(game.getWidth()*3/4, game.getHeight() - 80, 90, speed, KeyEvent.VK_A, KeyEvent.VK_D, Color.RED);
        Timer timer = new Timer(15, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        timePassed = 0;
    }

    public Plane getPlayer(int playerNo) {
        if (playerNo == 1)
            return player1;
        else
            return player2;
    }

    private void update() {
    	//System.out.print("Plane1: ");
        player1.update(player2);
    	//System.out.print("Plane2: ");
        player2.update(player1);
        repaint();
        timePassed += 0.015;
        if (timePassed >= 30){
        	if(player1.getLength()>player2.getLength()){
        		JOptionPane.showMessageDialog(null, "Player 1 wins!", "RC Combat Sim", JOptionPane.PLAIN_MESSAGE);
            	System.exit(0);
        	}
        	else if(player2.getLength()>player1.getLength()){
        		JOptionPane.showMessageDialog(null, "Player 2 wins!", "RC Combat Sim", JOptionPane.PLAIN_MESSAGE);
            	System.exit(0);
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "It's a tie", "RC Combat Sim", JOptionPane.PLAIN_MESSAGE);
            	System.exit(0);
        	}
        }
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

	
    public void keyPressed(KeyEvent e) {
        player1.pressed(e.getKeyCode());
        player2.pressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
    	;
    }

    public void keyTyped(KeyEvent e) {
        ;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(player1.getLength() + " : " + player2.getLength(), game.getWidth() / 2, 10);//DRAWS THE LENGTHS
        player1.paint(g);
        player2.paint(g);
    }
}