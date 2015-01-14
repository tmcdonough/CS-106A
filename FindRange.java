/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	private static double LARGEST = Double.NEGATIVE_INFINITY;
	private static double SMALLEST = Double.POSITIVE_INFINITY;
	private static boolean CHECK = true;
	
	public void run() {
		while(CHECK) {
			// the loop continues to accept new inputs until "CHECK" evaluates to FALSE
			// CHECK will evaluate to FALSE when the SENTINEL value is entered
			CHECK = iterationCheck(readInt("? "));
		}
	}

	private boolean iterationCheck(int inputValue) {
		if (inputValue==SENTINEL) {
			// in the event that the SENTINEL is entered, we check to see if it is the first iteration or not...
			// ...the first number entered (assuming it is not the sentinel) would be larger than negative infinity and smaller than positive infinity, the initial values for LARGEST/SMALLEST.
			// ...thus, LARGEST/SMALLEST will only be equal to NEGATIVE/POSITIVE INFINITY prior to the first number being entered.
			// ...so, if the statement in the if loop is true (SENTINEL is entered) AND LARGEST is equal to NEGATIVE INFINITY, then this is the first number entered
			// ...and we want the program to print out "N/A"s for largest and smallest.
	
			if (LARGEST==Double.NEGATIVE_INFINITY) {
				// the program only executes these items if the sentinel appears on the first go.
				// we enter false at the end so that the while loop terminates and the program ends.
				
				println("smallest: N/A");
				println("largest: N/A");
				return false;
			} else {
				// whenever else the sentinel appears, we've reached the end of the program.
				// because all integers are larger than negative infinity and smaller than positive infinity, we know that both LARGEST & SMALLEST will be equal to an integer
				// so, we feel comfortable printing out those values as integers.
				// we enter false at the end so that the while loop terminates and the program ends.
				
				printFinalNumbers(LARGEST,SMALLEST);
				return false;
			}
		} else {
			// if the sentinel is not entered, we compare the value entered to the current largest/smallest numbers.
			// in the beginning, any integer entered will be larger than negative infinity and smaller than positive infinity, so the first number entered will necessarily
			// ...be both LARGEST and SMALLEST (fulfills the requirement that if only one number is entered prior to sentinel value, that number is largest and smallest).
			// ...otherwise, each time a number is entered it is compared and LARGEST/SMALLEST are changed.
			// We return true at the end to ensure that the while loop continues and the user is asked for a new number.
			
			LARGEST = evalLargestNumber(inputValue,LARGEST);
			SMALLEST = evalSmallestNumber(inputValue,SMALLEST);
			return true;
		}
	}
	private double evalLargestNumber(int challenger, double contender) {
		// because infinity/negative infinity are doubles, we need to keep largest/smallest stored as doubles, even though the input will be an integer...
		// hence, we convert the input number (challenger) to a double within this method and return the contender as a double.
		// we will eventually apply a cast to the double to output as an integer
		// because this only happens once infinity/negative infinity have been removed (otherwise we print out NAs), cast should always work.
		
		if ((double)challenger>contender) {
			return (double)challenger;
		} else {
			return contender;
		}
	}
	private double evalSmallestNumber(int challenger, double contender) {
		// see above comments for evalLargestNumber
		
		if ((double)challenger<contender) {
			return (double)challenger;
		} else {
			return contender;
		}
	}
	private void printFinalNumbers(double LARGEST, double SMALLEST) {
		// before printing, we convert the LARGEST/SMALLEST doubles to integers
		
		int largestNumber = (int)LARGEST;
		int smallestNumber = (int)SMALLEST;
		println("smallest: "+String.valueOf(smallestNumber));
		println("largest: "+String.valueOf(largestNumber));
	}
}


