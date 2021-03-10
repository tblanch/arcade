 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Streamer {

	private ArrayList<Integer> xList;
	private ArrayList<Integer> yList;
	//private Color c;
	private int numSections;

    public Streamer(Color c1) {
       xList = new ArrayList<Integer>();
       yList = new ArrayList<Integer>();
       //c = c1;
       numSections = 80;
    }

    public void update(int x, int y, Plane p) {
    	xList.add(0, x);
    	yList.add(0, y);
    	if(xList.size() > numSections && yList.size() > numSections){
    		xList.remove(xList.size()-1);
    		yList.remove(yList.size()-1);
    	}
    	checkCollision(p);
    	if(numSections < xList.size()){
    		for(int j = xList.size()-1; j >= numSections; j--){
    			xList.remove(j);
    			yList.remove(j);
    		}
    	}
    	//System.out.print("Size = " + xList.size());
    	//System.out.println(" numSections=" + numSections);
    }

    public void checkCollision(Plane p) {
    	if(numSections > 1){
    		for(int i = 0; i < xList.size()-1; i++){
    			Line2D l = new Line2D.Double(xList.get(i), yList.get(i), xList.get(i+1), yList.get(i+1));
    			if(p.getBounds().intersectsLine(l)){
    					numSections = i;
    			}
    		}
    	}
    }

    public void paint(Graphics g) {
    	
    	for(int i = 0; i < xList.size()-1; i++){
    		g.drawLine(xList.get(i), yList.get(i), xList.get(i+1), yList.get(i+1));
    	}
    }
    
    public int getLength(){
    		int out = 0;
    		for(int i = 0; i < xList.size()-1; i++){
    			out += (int)Math.sqrt(Math.pow(xList.get(i+1)-xList.get(i) , 2) + Math.pow(yList.get(i+1)-yList.get(i) , 2));
    		}
    		return out;
    }
}

/*int[] xs = new int[xList.size()];
int[] ys = new int[yList.size()];
for(int j = 0; j<xList.size(); j++){
	xs[j] = xList.get(j);
	ys[j] = yList.get(j);
}
g.drawPolyline(xs, ys, xs.length);*/