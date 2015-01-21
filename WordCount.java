/*
 * File: WordCount.java
 * ------------------
 * Section Handout 5 - Question 1
 * 
 */

import java.io.*;

import acm.program.*;
import acm.util.ErrorException;


public class WordCount extends ConsoleProgram {
	
	private final static String prompt = "File: ";
	
	public void run() {
		rd = openFile(prompt);
		scanFileForCounts(rd);
		printOutput();
	}
	private BufferedReader openFile(String prompt) {
		BufferedReader rd = null;
		while (rd==null) {
			try {
				String name = readLine(prompt);
				rd = new BufferedReader(new FileReader(name));
			} catch (IOException ex) {
				println("Bad file.");
			}
		}
		return rd;
	}
	private void scanFileForCounts(BufferedReader rd){
		try {
			while (true) {
				String line = rd.readLine();
				if (line==null) break;
				// once we know that the line has content, we first add to the word and char counts for the appropriate number of each from this line
				for (int i=0;i<line.length();i++){
					char c = line.charAt(i);
					chars++;
					if (Character.isLetterOrDigit(c)==false) {
						if(Character.isLetterOrDigit(lastChar)==true) words++; // this just makes sure to not add two words in cases where, for example, a space follows a period.
					}
					if (Character.isUpperCase(c)==true) { // for instances where there is no punctuation at the end of a line, this will make sure to still add the word
						if(Character.isLetterOrDigit(lastChar)==true) words++;
					}
					lastChar = c;
				}
				//then we add to the lines count
				lines++;
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	private void printOutput() {
		println("Lines = "+lines);
		println("Words = "+words);
		println("Chars = "+chars);
	}
	
	private BufferedReader rd;
	private char lastChar;
	private int lines;
	private int words;
	private int chars;
}
	