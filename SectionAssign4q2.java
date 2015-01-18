/*
 * File: SectionAssign4q2.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * 
 */


import acm.program.*;



public class SectionAssign4q2 extends ConsoleProgram {
	public void run() {
		while(true) {
			String enteredString = readLine("Enter a string to be fixed: ");
			String enteredChar = readLine("Enter a single character to remove: ");
			char offendingChar = enteredChar.charAt(0);
			println(removeAllOccurances(enteredString, offendingChar));
		}
	}
	public String removeAllOccurances(String str, char ch) {
		String newString = "";
		for (int i = 0;i<str.length();i++){
			char chInString = str.charAt(i);
			if (chInString!=ch) {
				newString+=chInString;
			}
		}
		return newString;
	}
}