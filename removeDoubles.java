
import acm.program.*;

public class removeDoubles extends ConsoleProgram {
	
	public void run() {
		while(true){
			String input = readLine("Enter a word (or enter END to end): ");
			if (input.equals("END"))break;
			println(removeDoubledLetters(input));
		}
	}
	
	private String removeDoubledLetters(String input){
		char lastChar = 0;
		String newString = "";
		for (int i=0;i<input.length();i++){
			if(lastChar==0){
				lastChar=input.charAt(i);
				newString+=lastChar;
			} else {
				if (input.charAt(i)!=lastChar){
					lastChar = input.charAt(i);
					newString+=lastChar;
				}
			}
		}
		return newString;
	}
	
	
	
	

}
