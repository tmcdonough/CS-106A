/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		putBeeper();
		turnLeft();
		if (rightIsBlocked()) {
			upColumn();
		}
		while (rightIsClear()) {
			if (frontIsClear()) {
				if (leftIsBlocked()){
					upColumn();
				} else {
					nextColumnBottom();
					upColumn();
				}
			} else {
				nextColumnTop();
				downColumn();
			}
		}

			}
	private void upColumn() {
		if (beepersPresent()) {
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
					}
				}
		} else {
			while (frontIsClear()) {
				move();
				putBeeper();
				if (frontIsClear()) {
					move();
				}	
			}	
		}
	}

	private void downColumn() {
		if (beepersPresent()) {
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
					}
				}
		} else {
			while (frontIsClear()) {
				move();
				putBeeper();
				if (frontIsClear()) {
					move();
				}	
			}	
		}
		turnAround();
	}
	
	private void nextColumnTop() {
		if (beepersPresent()) {
			turnRight();
			move();
			turnRight();	
		} else {
			turnRight();
			move();
			putBeeper();
			turnRight();
		}
	}
	private void nextColumnBottom() {
		if (beepersPresent()) {
			turnRight();
			move();
			turnLeft();
		} else {
			turnRight();
			move();
			putBeeper();
			turnLeft();
		}
	}
}
