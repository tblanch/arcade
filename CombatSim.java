 

import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;

public class CombatSim extends JFrame {
    private final static int WIDTH = 1600, HEIGHT = 1000; // sets the size of the GUI panel
    private SkyPanel panel;
 
    public CombatSim() {
        setSize(WIDTH, HEIGHT);
        setTitle("RC Plane Combat Sim");
        setBackground(Color.BLUE);
        //setResizable(false);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new SkyPanel(this);
        Container c = getContentPane();
        c.add(panel);
        //add(panel);
         setVisible(true);
    }

    public SkyPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new CombatSim();
    }
}