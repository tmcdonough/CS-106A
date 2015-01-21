/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.program.*;
import acm.util.ErrorException;

public class HangmanLexicon extends ConsoleProgram {

/** Returns the number of words in the lexicon. */
	public HangmanLexicon() {
		rd = openFile("HangmanLexicon.txt");
		readWords(rd);
	}
	
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	}
	
	private BufferedReader openFile(String file) {
		BufferedReader rd = null;
		boolean badFile = false;
		while (rd==null) {
			try {
				if (!badFile){
					rd = new BufferedReader(new FileReader(file));
				} else if (badFile) {
					rd = new BufferedReader(new FileReader(readLine("That file was bogus, enter a new one: ")));
				}
				
			} catch (IOException ex) {
				badFile = true;
				println("Bad file.");
			}
		}
		return rd;
	}
	
	private void readWords(BufferedReader rd) {
		words = new ArrayList<String>();
		try {
			while(true) {
				String word = rd.readLine();
				if (word==null) break; // will break out of loop when the end of the file is reached.
				words.add(word);
			} 
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private ArrayList<String> words;
	private BufferedReader rd; 
}