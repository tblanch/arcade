/**
 * This program contains the main menu and interface for the Ultimate Arcade.
 * This program connects to each of the other games and the jukebox using user input
 * 
 * @author  Tucker B
 * @version 1.00 2018/05/22
 */

import javax.swing.*;
import java.awt.*;
import RcPlaneCombatSimulator.*;
import UltimatePong.*;
import UltimateTetris.*;
import UltimateJukebox.*;


public class UltimateArcade
{
	public static void main(String[] args) throws Exception {
		JOptionPane.showMessageDialog(null, "Welcome to the Ultimate Arcade by:Tucker Blanch\nPress OK to continue.", "Welcome", JOptionPane.PLAIN_MESSAGE);
		Object[] options = {"RC Plane Combat Simulator", "Ultimate Pong", "Ultimate Tetris", "Jukebox/Music", "Exit"};
		//while(true) {
			int choice = JOptionPane.showOptionDialog(null, "This is the main menu of the Ultimate Arcade.\nYou can choose to play any of the games we have, change the music, or exit.\nHave fun!",
					"Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if(choice == 0) {
				CombatSim.main(args);
			}
			else if(choice == 1) {
				Pong.main(args);
			}
			else if(choice == 2) {
				Arcade.main(args);
			}
			else if(choice == 3) {
				UltimateJukebox.main(args);
			}
			else if(choice == 4) {
				JOptionPane.showMessageDialog(null, "I'm sorry to hear that you want to leave.\nI hope you had fun.\nGoodbye", "Exit", JOptionPane.WARNING_MESSAGE);
				//break;
			}
		//}
	}

}
