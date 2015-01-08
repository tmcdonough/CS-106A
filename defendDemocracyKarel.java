
import stanford.karel.*;

public class defendDemocracyKarel extends SuperKarel {
	public void run() {
		move(); // Since ballots are two spaces apart, and the first ballot is just one space from the wall, we move Karel to start to eliminate OBOEs
		cleanBallots(); // "Cleaning" is defined as checking ballots and, if applicable, removing excess chads
	}
	private void cleanBallots() {
		while (frontIsClear()) { // there can be no more ballots once karel has reached the eastern wall
			evaluateVote(); // we move karel to the first ballot at the beginning of the program, and the loop will always return him to a ballot. So, the first step for Karel is to determine if the ballot has been filled in
			cleanUp(); // if the ballot has been filled in, then Karel checks for excess chads
		}
	}
	private void evaluateVote(){
		while (beepersPresent()) { // if a beeper is present, then there is nothing to clean up since the ballot had not been filled in...
			moveToNext(); // ...so we have Karel move on to the next Ballot until (hence the while loop) we land at a voted ballot
		}
		
	}
	private void moveToNext() {
		move(); // there will always be at least one space after a ballot (normally, there are two, but after the final ballot there is one space between the ballot and the wall).
		if (frontIsClear()) { // this checks to make sure we have not reached the end of the ballots.
			move(); // if we have not, this brings us to the next ballot
		}
	}
	private void cleanUp() {
		if (frontIsClear()); { // not strictly necessary, since nothing in the body would break if we were between the final ballot and the eastern wall, but theoretically is more efficient.
			cleanTop(); // assuming the front is clear, we must be on a ballot that has been filled in. So, we want to clean the top of the ballot to remove chads
			cleanBottom(); // we do the same with the bottom
			moveToNext(); // then we move on to the next ballot
		}
	}
	private void cleanTop() {
		sideStepLeft(); // moves Karel up (to the top of the ballot) when facing east (maintains direction)
		cleanUpChads(); // removes all Chad
		sideStepRight(); // move Karel down (back to the center of the ballot) when facing east (maintains direction)
	}
	private void cleanBottom() {
		sideStepRight(); // moves karel down (to the bottom of the ballot) when facing east (maintains direction)
		cleanUpChads(); // removes all Chad
		sideStepLeft(); // moves Karel up (back to the center of the ballot) when facing east (maintains direction)
	}
	private void sideStepLeft() {
		turnLeft();
		move();
		turnRight();
	}
	private void sideStepRight() {
		turnRight();
		move();
		turnLeft();
	}
	private void cleanUpChads() {
		while (beepersPresent()){
			pickBeeper(); // will continue to pick up beepers until none are left
		}
	}
	

}
