/*
 * File: SecAssign3Q3.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import acm.util.*;

// 1) a. True
//	  b. False
// 2) Bludger: x = 1000.75, y = 2001, z = 2003.001
// 3) is below:


public class SecAssign3Q3 extends GraphicsProgram {
	
	private static final int numCircles = 10;
	private static final int radiusMin = 5;
	private static final int radiusMax = 50;
	RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void run () {

		int windowWidth = getWidth();
		int windowHeight = getHeight();
		for(int i=0; i<numCircles; i++) {
			add(drawACircle(windowWidth, windowHeight));
		}
	}
	private GOval drawACircle(int graphicsAreaWidth, int graphicsAreaHeight) {
		double radius = rgen.nextDouble(radiusMin,radiusMax);
		double xCoord = rgen.nextDouble(0,graphicsAreaWidth-radius*2);
		double yCoord = rgen.nextDouble(0,graphicsAreaHeight-radius*2);
		GOval circle = new GOval(xCoord,yCoord,radius,radius);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		return circle;
	}

	private Color randomColor(RandomGenerator rgen) {
		int r = rgen.nextInt(255);
		int g = rgen.nextInt(255);
		int b = rgen.nextInt(255);
		return new Color(r, g, b);
	}
}
