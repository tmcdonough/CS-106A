import stanford.karel.SuperKarel;

public class MidtermQuestion1Karel extends SuperKarel {
	public void run() {
		checkBottomRows();
		putSide();
		putSide();
		putSide();
		putSide();
	}
	private void checkBottomRows() {
		move();
		while (true) {
			move();
			if(frontIsBlocked()) {
				turnAround();
				move();
				turnRight();
				move();
				putBeeper();
				break;
			}
		}
		turnLeft();
	}
	private void putSide(){
		move();
		if(frontIsClear()){
			if(noBeepersPresent())putBeeper();
		}
		while(true){
			if(frontIsBlocked()){
				turnAround();
				move();
				turnLeft();
				break;
			}else{
				move();
				if(beepersPresent())break;
				if(frontIsBlocked()){
					turnAround();
					move();
					turnLeft();
					break;
				}
				putBeeper();
			}
		}
	}
}