import acm.program.*;

public class MidtermQuestion3 extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		printInstructions();
		getInputs();
		printResults();
	}
	private void printInstructions(){
		println("This program finds the two largest integers in a list. Enter values, one per line, using a 0 to signal the end of the list.");
	}
	private void getInputs() {
		while (true) {
			entry = readInt("?");
			if (entry==SENTINEL){
				break;
			} else if (entry>=largest){
				secondLargest = largest;
				largest = entry;
			} else if (entry>secondLargest){
				secondLargest = entry;
			}
		}
	}
	private void printResults(){
		println("The largest value is "+largest);
		println("The second largest is "+secondLargest);
	}
	
	private int entry;
	private int largest;
	private int secondLargest;

}
