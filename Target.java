/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		
		//We first determine the size of the frame
		int windowWidth = getWidth();
		int windowHeight = getHeight();
		
		//We then set the sizes of our circles
		double largeCircleRadius = 1.0*72;
		double mediumCircleRadius = 0.65*72;
		double smallCircleRadius = 0.3*72;
		
		//and their colors
		Color largeCircleColor = Color.RED;
		Color mediumCircleColor = Color.WHITE;
		Color smallCircleColor = Color.RED;
		
		//then we add them to the frame, starting with the largest circle
		add(createCircle(windowWidth,windowHeight,largeCircleRadius,largeCircleColor));
		add(createCircle(windowWidth,windowHeight,mediumCircleRadius,mediumCircleColor));
		add(createCircle(windowWidth,windowHeight,smallCircleRadius,smallCircleColor));
	}
	
	// this method will create a circle in the center of a frame, based on given width/height, with a given radius and filled in with a given color. Thus, it works for all three circles.
	private GOval createCircle(int windowWidth, int windowHeight, double radius, Color color) {
		double width = radius*2;
		double height = width;
		double x = (double)windowWidth/2 - radius;
		double y = (double)windowHeight/2 - radius;
		GOval circle = new GOval(x,y,width,height);
		circle.setFilled(true);
		circle.setFillColor(color);
		circle.setColor(color);
		return circle;
	}
}
