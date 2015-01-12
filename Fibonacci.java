/*
 * File: Fibonacci.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * This file solves 2-1: The Fibonacci sequence
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	
	// declares three variables that are used to compute the next fibonacci number: the current fibonacci number, the last fibonacci number, and the fibonacci number two iterations ago.
	int prior2Fib = 0;
	int priorFib = 0;
	int currentFib = 0;
	
	public void run() {
		// because the first two fibonacci numbers (0, 1) do not have preceeding iterations to sum, we simply print 0 and 1 (and then set prior2Fib to 0, priorFib to 1).
		firstTwoFibs();
		// after the initial two fibonacci numbers, the program will sum the prior two iterations and print. The program will not print a number >= 10,000, and will break at that point.
		remainingFibs();
			}
	public void firstTwoFibs() {
		println(0);
		println(1);
		priorFib = 1;
	}
	public void remainingFibs() {
		while (true) {
			currentFib = prior2Fib + priorFib;
			prior2Fib = priorFib;
			priorFib = currentFib;
			if (currentFib < LARGESTFIB) {
				println(currentFib);
			} else {
				break;
			}
		}
	}
	private static final int LARGESTFIB = 10000;
}
