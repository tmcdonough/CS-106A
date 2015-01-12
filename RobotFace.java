/*
 * File: RobotFace.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * This file solves 2-2: Drawing a Face
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	
	public void run() {
		
		// gets the width/height of the graphics area
		int windowWidth = getWidth();
		int windowHeight = getHeight();
		
		// determines the x, y coords for head such that the center of the head is in the center of the graphics area, then adds the head
		int headPosX = windowWidth/2 - HEAD_WIDTH/2;
		int headPosY = windowHeight/2 - HEAD_HEIGHT/2;
		add(createRobotHead(headPosX,headPosY,HEAD_WIDTH,HEAD_HEIGHT));
		
		// determines the x, y coords for mouth such that the center of the mouth is 3/4ths down the head and centered horizontally, then adds the mouth
		int mouthPosX = windowWidth/2 - MOUTH_WIDTH/2;
		int mouthPosY = windowHeight/2 + HEAD_HEIGHT/4 - MOUTH_HEIGHT/2;
		add (createRobotMouth(mouthPosX,mouthPosY,MOUTH_WIDTH,MOUTH_HEIGHT));
		
		// determines the x, y coords for left eye such that the center of the eye is 1/4th down the head and 1/4th across the head from the left, then adds the eye
		int leftEyePosX = windowWidth/2 - HEAD_WIDTH/4 - EYE_RADIUS;
		int leftEyePosY = windowHeight/2 - HEAD_HEIGHT/4 - EYE_RADIUS;
		add (createRobotEye(leftEyePosX,leftEyePosY,EYE_RADIUS*2,EYE_RADIUS*2));
		
		// determines the x, y coords for right eye such that the center of the eye is 1/4th down the head and 3/4ths across the head from the left, then adds the eye
		int rightEyePosX = windowWidth/2 + HEAD_WIDTH/4 - EYE_RADIUS;
		int rightEyePosY = windowHeight/2 - HEAD_HEIGHT/4 - EYE_RADIUS;
		add (createRobotEye(rightEyePosX,rightEyePosY,EYE_RADIUS*2,EYE_RADIUS*2));
	}
		
	private GRect createRobotHead(double x, double y, int width, int height) {
		GRect head = new GRect(x, y, width, height);
		head.setColor(Color.BLACK);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		return head;
	}
	
	private GRect createRobotMouth(double x, double y, int width, int height) {
		GRect mouth = new GRect(x,y,width,height);
		mouth.setColor(Color.WHITE);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		return mouth;
	}
	
	private GOval createRobotEye(double x, double y, int width, int height) {
		GOval eye = new GOval(x,y,width,height);
		eye.setFilled(true);
		eye.setFillColor(Color.YELLOW);
		eye.setColor(Color.YELLOW);
		return eye;
	}
	
	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 200;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 50;
	private static final int MOUTH_HEIGHT = 20;
	private static final double WINDOW_CENTER_X = 50;
	private static final double WINDOW_CENTER_Y = 50;
}
