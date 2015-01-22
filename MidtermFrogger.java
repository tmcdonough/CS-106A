
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MidtermFrogger extends GraphicsProgram {
	
	private final static int SQSIZE = 75;
	private final static int NCOLS = 7;
	private final static int NROWS = 3;
	private static final int APPLICATION_WIDTH = NCOLS*SQSIZE;
	private static final int APPLICATION_HEIGHT = NROWS*SQSIZE;
	
	public void run() {
		addMouseListeners();
		frog = getFrogger("frog.jpg");
		putFrogger(frog);
	}
	
	private GImage getFrogger(String file){
		frog = new GImage(file);
		frog.setSize(SQSIZE,SQSIZE);
		return frog;
	}
	
	private void putFrogger(GImage frog){
		x = getWidth()/2-SQSIZE/2;
		y = getHeight()-SQSIZE;
		frog.setLocation(x,y);
		add(frog);
	}
	
	public void mouseClicked(MouseEvent e){
		double xClick = e.getX();
		double yClick = e.getY();
		double xCurr = frog.getX();
		double yCurr = frog.getY();
		double xMove = xClick - xCurr;
		double yMove = yClick - yCurr;
		double absXmove = Math.abs(xMove);
		double absYmove = Math.abs(yMove);
		if (absXmove > absYmove){
			if(xMove<0){
				if(xCurr+xMove>0){
					frog.move(-SQSIZE,0);
				}
			} else {
				if(xCurr+xMove<getWidth()-SQSIZE){
					frog.move(SQSIZE,0);
				}
			}
		} else {
			if (yMove<0){
				if(yCurr+yMove>0){
					frog.move(0,-SQSIZE);
				}
			} else {
				if(yCurr+yMove<getHeight()-SQSIZE){
					frog.move(0,SQSIZE);
				}
			}
		}
	}
	
	private GImage frog;
	private double x,y;
}