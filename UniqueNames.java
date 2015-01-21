/*
 * File: UniqueNames.java
 * ------------------
 * Section Handout 5 - Question 3
 * 
 */

import java.util.ArrayList;
import acm.program.*;


public class UniqueNames extends ConsoleProgram {
	
	public void run() {
		getNames();
		printNames();
	}
	
	private void getNames() {
		names = new ArrayList<String>();
		while (true) {
			nameEntry = readLine("Enter name: ");
			if (nameEntry.isEmpty()) break;
			if (names.contains(nameEntry)) continue;
			names.add(nameEntry);
		}
		
	}
	
	private void printNames() {
		println("Unique name list contains: ");
		for (int i = 0; i < names.size();i++ ) {
			println(names.get(i));
		}
	}
	
	private ArrayList<String> names;
	private String nameEntry;
}