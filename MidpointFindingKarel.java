/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {		
		if (frontIsClear()) {
			startBeeperRun();
			if (facingEast()) {
				moveToWall();
				moveBackward();
				putBeeper();
				while (facingEast()) {
					findBeeperBackward();
					placeNewBeeperLeft();
					if (facingEast()) {
						findBeeperForward();
						placeNewBeeperRight();	
					}
				}
			}
			
		} else {
			putBeeper();
		}
		
}
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}
	private void moveBackward() {
		turnAround();
		move();
		turnAround();
	}
	private void endRun() {
		if (facingEast()) {
			turnAround();	
		}
	}
	private void startBeeperRun() {
		move();
		putBeeper();
		for (int i=0;i<2;i++) {
			if (frontIsBlocked()) {
				endRun();
			}
			if (facingEast()) {
				move();
			}
		}
		if (frontIsBlocked()) {
			endRun();
		}
		if (facingEast()) {
			move();
		}
	}
	private void findBeeperBackward() {
		moveBackward();
		while (noBeepersPresent()) {
			moveBackward();
		}
		pickBeeper();
	}
	private void findBeeperForward() {
		move();
		while (noBeepersPresent()) {
			move();
		}
		pickBeeper();
	}
	private void placeNewBeeperLeft() {
		move();
		if (beepersPresent()) {
			endRun();
		} else {
			putBeeper();
		}
	}
	private void placeNewBeeperRight() {
		moveBackward();
		if (beepersPresent()) {
			endRun();
		} else {
			putBeeper();
		}
	}
	// You fill in this part

}
