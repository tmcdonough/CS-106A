/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		//step 1 = get a number from the user
		
		int userInputNumber = getInputNumber();
		
		// Next, we check to make sure that the user has not entered 1 to start.
		// if she has, we end the program. Otherwise, we continue into a while loop until the hailstone sequence ends in 1.
		
		if (checkIfEqualToOne(userInputNumber)) {
			println("Spoiler alert: you entered 1. Try again.");
		} else {
			int currentNumber = userInputNumber; // we just reassign to make the code easier to read within the loop. We could have left this as "userInputNumber".
			while (true) { // loop and a half... (ends when we break from the loop)
				boolean odd = checkIfOdd(currentNumber);
				// after checking if the last number in the loop is odd or even, we overwrite the number by applying the appropriate instructions. Note that if odd evaluates to false, it will execute the even instructions.
				currentNumber = applyInstructions(odd, currentNumber);
				// it then checks to make sure the new number isn't 1, and then loops again (otherwise, it exits).
				if (checkIfEqualToOne(currentNumber)) {
					break;
				}
			}
		}
	}
	private int getInputNumber(){
		return readInt("Enter a number: ");
	}
	private boolean checkIfOdd(int inputNumber) {
		if (inputNumber%2==0){
			return false;
		} else {
			return true;
		}
	}
	private int applyInstructions(boolean Odd, int inputNumber) {
		if (Odd) {
			int outputNumber = applyOddInstructions(inputNumber);
			return outputNumber;
		} else {
			int outputNumber = applyEvenInstructions(inputNumber);
			return outputNumber;
		}
	}
	private int applyOddInstructions(int inputNumber) {
		int newNum = 3*inputNumber+1;
		println(newNum);
		return newNum;
	}
	private int applyEvenInstructions(int inputNumber) {
		int newNum = inputNumber/2;
		println(newNum);
		return newNum;
	}
	private boolean checkIfEqualToOne(int input) {
		if (input==1){
			return true;
		} else {
			return false;
		}
	}
}
	

