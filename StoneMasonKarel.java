/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		buildColumns();
	}
	private void dropBeeper() {
		/*
		 * if no beeper in current corner, drop a beeper
		 * (i.e., does not check for column)
		 */
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
	private void upColumn() {
		turnLeft();
		while (frontIsClear()) {
			dropBeeper();
			move();
		}
		dropBeeper();
	}
	private void downColumn() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
	}
	private void nextColumn() {
		turnLeft();
		move();
		move();
		move();
		move();
	}
	private void buildColumns() {
		while (frontIsClear()) {
			upColumn();
			downColumn();
			nextColumn();
		}
		upColumn();
		downColumn();
	}
}
