/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import java.io.Console;

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	public void run() {
		println("Enter values to compute Pythagorean theorem");
		int a = getSideInput("a");
		int b = getSideInput("b");
		println(returnLongestSide(a,b));
	}
	private double returnLongestSide(int a, int b) {
		// "a squared plus b squared equals c squared..."
		return Math.sqrt((double)a*(double)a+(double)b*(double)b);
	}
	private int getSideInput(String side) {
		// get the length of a side of the triangle, based on the name of the side included as an arg (e.g., a or b)
		int lengthOfSide = readInt(side+": ");
		return lengthOfSide;
	}

}
