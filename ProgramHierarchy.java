/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 40;
	private static final double VERTICAL_GAP = 40;
	private static final double HORIZONTAL_GAP = 20;
	
	public void run() {
		
		//We first determine the size/center of the frame
		int windowWidth = getWidth();
		int windowHeight = getHeight();
		double centerX = (double)windowWidth/2;
		double centerY = (double)windowHeight/2;
		
		// Next, we determine the appropriate coordinates for our three types of GObjects: 4 boxes, 4 labels, and 3 lines
		double topBoxCoordY = centerY - (BOX_HEIGHT*2+VERTICAL_GAP)/2;
		double topBoxCoordX = centerX - (BOX_WIDTH/2);
		
		double bottomLeftBoxCoordX = topBoxCoordX - HORIZONTAL_GAP - BOX_WIDTH;
		double bottomLeftBoxCoordY = topBoxCoordY + VERTICAL_GAP + BOX_HEIGHT;
		
		double bottomCenterBoxCoordX = topBoxCoordX;
		double bottomCenterBoxCoordY = bottomLeftBoxCoordY;
		
		double bottomRightBoxCoordX = topBoxCoordX + HORIZONTAL_GAP + BOX_WIDTH;
		double bottomRightBoxCoordY = bottomLeftBoxCoordY;
		
		double lineStartCoordX = centerX;
		double lineStartCoordY = centerY - (VERTICAL_GAP/2);
		
		double lineEndCoordY = centerY + VERTICAL_GAP/2;
		
		double leftLineEndCoordX = centerX - BOX_WIDTH - HORIZONTAL_GAP;
		double rightLineEndCoordX = centerX + BOX_WIDTH + HORIZONTAL_GAP;
		
		// We set the label contents
		String topLabel = "Program";
		String botLeftLabel = "GraphicsProgram";
		String botCentLabel = "ConsoleProgram";
		String botRightLabel = "DialogProgram";
		
		//then we add the boxes, lines, and text to the graphics window
		add(createLabelBox(topBoxCoordX,topBoxCoordY));
		add(createLabelBox(bottomLeftBoxCoordX,bottomLeftBoxCoordY));
		add(createLabelBox(bottomCenterBoxCoordX,bottomCenterBoxCoordY));
		add(createLabelBox(bottomRightBoxCoordX,bottomRightBoxCoordY));
		
		add(createLine(lineStartCoordX,lineStartCoordY,leftLineEndCoordX,lineEndCoordY));
		add(createLine(lineStartCoordX,lineStartCoordY,lineStartCoordX,lineEndCoordY));
		add(createLine(lineStartCoordX,lineStartCoordY,rightLineEndCoordX,lineEndCoordY));
		
		add(createLabel(topLabel, topBoxCoordX, topBoxCoordY, BOX_WIDTH, BOX_HEIGHT));
		add(createLabel(botLeftLabel, bottomLeftBoxCoordX,bottomLeftBoxCoordY, BOX_WIDTH, BOX_HEIGHT));
		add(createLabel(botCentLabel, bottomCenterBoxCoordX,bottomCenterBoxCoordY, BOX_WIDTH, BOX_HEIGHT));
		add(createLabel(botRightLabel, bottomRightBoxCoordX,bottomRightBoxCoordY, BOX_WIDTH, BOX_HEIGHT));
		
		/* You fill this in. */
	}
	private GRect createLabelBox(double x,double y) {
		GRect labelBox = new GRect(x,y,BOX_WIDTH,BOX_HEIGHT);
		return labelBox;
	}
	private GLine createLine(double xStart,double yStart,double xEnd,double yEnd) {
		GLine connector = new GLine(xStart,yStart,xEnd,yEnd);
		return connector;
	}
	private GLabel createLabel(String string, double x, double y, double boxWidth, double boxHeight) {
		GLabel label = new GLabel(string,x,y);
		double labelAscent = label.getAscent();
		double labelWidth = label.getWidth();
		GLabel labelNew = new GLabel(string,x+(boxWidth-labelWidth)/2,y+(boxHeight-labelAscent));
		return labelNew;
	}
}

