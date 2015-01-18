/*
 * File: SecAssign3Q4.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * Draw a line
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;


public class SecAssign3Q4 extends GraphicsProgram {
	
	public void init() {
		addMouseListeners();
		addKeyListeners();
	}
	public void mousePressed(MouseEvent e) {
		Last = new GPoint(e.getPoint());
		Line = new GLine(Last.getX(), Last.getY(), Last.getX(), Last.getY());
		add(Line);
	}
	public void mouseDragged(MouseEvent e) {
		if (Line != null) {
			Last = new GPoint(e.getPoint());
			Line.setEndPoint(Last.getX(), Last.getY());
		}
	}
	public void keyTyped(KeyEvent e) {
		removeAll();
	}
	
	/* Private instance variables */
	private GPoint Last;
	private GLine Line = null;
}