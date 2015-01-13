/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 25;

	public void run() {
		
		// gets the dimensions of the frame
		int windowWidth = getWidth();
		int windowHeight = getHeight();
		
		// sets the starting x,y coordinates for the top brick. X is half a brick to the left of the center of the frame. Y is the number of bricks times the height of a brick North of the bottom of the frame.
		double yPosCurr = (double)windowHeight - (double)BRICKS_IN_BASE*(double)BRICK_HEIGHT;
		double xPosCurr = ((double)windowWidth/2.0-(double)BRICK_WIDTH/2.0);
		
		// the number of bricks in the base is equal to number of rows. So, we add that many rows by incrementing up to that number. We start at 1 (instead of 0) since the row number also corresponds to the number of bricks in each row
		for (int i=1; i<(BRICKS_IN_BASE+1); i++) {
			// since i is equal to both the row number and the number of bricks in that row, we add i many bricks (hence, j<i when j starts at 0)
			for (int j=0; j<i; j++) {
				if(j==0) {
					// the first brick is pre-set for the first row, and for every row thereafter we reset at the end of the prior row. Thus, x,y coords are already set for first brick.
					add(createBrick(xPosCurr,yPosCurr,BRICK_WIDTH,BRICK_HEIGHT));
				// otherwise, we need to re-adjust by adding the width of one brick to the prior x coordinate. Note that at the final brick, this if loop will end and...
				} else {
					xPosCurr = xPos(xPosCurr);
					add(createBrick(xPosCurr,yPosCurr,BRICK_WIDTH,BRICK_HEIGHT));
				}
			}
			//...the x and y coordinates will be reset for the next line
			xPosCurr = resetXPos(xPosCurr,i);
			yPosCurr = resetYPos(yPosCurr);
		}
	}
	private GRect createBrick(double x, double y, int width, int height) {
		GRect brick = new GRect(x, y, width, height);
		return brick;
	}
	public double xPos(double lastXPos) {
		// moves the x coordinate across the frame by the width of one brick, for intra-row shifts
		return lastXPos + (double)BRICK_WIDTH;
	}
	public double resetYPos(double lastYPos) {
		// moves the y coordinate south by the height of one brick, for when the program is moving onto a new row
		return lastYPos + (double)BRICK_HEIGHT;
	}
	public double resetXPos(double lastXPos, int numberOfBricksInRow) {
		// moves the x coordinate west to the starting point PLUS another half a brick (the bricks are laid on top of each other such that the ends lie in the center of the brick below).
		return (lastXPos - ((double)numberOfBricksInRow-0.5)*(double)BRICK_WIDTH);
	}
}

