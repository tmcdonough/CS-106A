/*
 * File: SectionAssign4q1.java
 * Name: Tom McDonough
 * Section Leader: N/A
 * -----------------------------
 * 
 */


import acm.program.*;



public class SectionAssign4q1 extends ConsoleProgram {
	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0){break;}
			println(addCommasToNumericString(digits));
			}
		}
	private String addCommasToNumericString(String string){
		String finalString = "";
		int numberCommas = (string.length()-1)/3;
		int commaCounter = 0;
		int lengthOriginal = string.length();
		int firstComma = lengthOriginal - 3*numberCommas;
		
		for (int i=0;i<(lengthOriginal+numberCommas);i++) {
			if (i==firstComma || (i-firstComma)%4==0) {
				finalString += ',';
				commaCounter++;
			} else {
				finalString+=string.charAt(i-commaCounter);
			}
		}
		return finalString;
	}
	}