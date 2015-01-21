/*
 * File: Histograms.java
 * ------------------
 * Section Handout 5 - Question 2
 * 
 */

import java.io.*;
import java.util.ArrayList;

import acm.program.*;
import acm.util.*;


public class Histograms extends ConsoleProgram {
	
	private static final int SCORE_START = 0;
	private static final int SCORE_END = 100;
	private static final int NUMBER_OF_SCORE_RANGES = 11;
	
	public void run() {
		scores = new int[NUMBER_OF_SCORE_RANGES];
		rd = openFile("Enter file name: ");
		scanFileForScores(rd);
		convertedScores = convertToAsterisk(scores,scores.length);
		displayHistogram(scores,scores.length);
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
	
	private void scanFileForScores(BufferedReader rd) {
		try {
			while(true) {
				scoreText = rd.readLine();
				if (scoreText==null) break; // will break out of loop when the end of the file is reached.
				try { // this just makes sure it's possible to convert the line to an integer, and if not it skips and tries to read the next line.
					score = Integer.parseInt(scoreText);
				} catch(NumberFormatException ex){
					continue;
				}
				scores[classifyScore(score)]++; // this will determine what range the test score is in, and will count up the number of scores in that range within the scores array.
			} 
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private String[] convertToAsterisk(int[] scores, int numberOfRanges){
		// because the histogram displays the number of scores in a range as a series of asterisks, this will add an asterisk to a string for the length of each score range, and then return a string array with the corresponding asterisks, for printing. 
		convertedScores = new String[numberOfRanges];
		for (int i = 0;i<scores.length;i++){
			String asteriskEntry = "";
			for (int j = 0;j<scores[i];j++) {
				asteriskEntry+="*";
			}
			convertedScores[i] = asteriskEntry;
		}
		return convertedScores;
	}
	
	private void displayHistogram(int[] scores, int numberOfRanges) {
		
		scoreDisplays = new String[numberOfRanges];
		
		// this part is not strictly necessary, but it theoretically will construct the current set of strings for a given number of ranges (e.g., "20-29")
		// because my classifyScore method assumes 11 ranges (incl. 100), this is useless, but it would be valuable if classifyScore were reconstructed to
		// work with multiple range amounts
		
		for (int i = 0;i<numberOfRanges;i++){
			if (i==0) {
				// for the first range, we want to start at 0 and add 
				rangeNum1 = SCORE_START;
				rangeNum2 = SCORE_END/(numberOfRanges-1)-1;
				rangeNum1toString = Integer.toString(rangeNum1);
				rangeNum2toString = Integer.toString(rangeNum2);
				if (rangeNum1toString.length()==1) { rangeNum1toString="0"+rangeNum1toString;}; // just adds a zero before the number if it is only one digit
				if (rangeNum2toString.length()==1) { rangeNum2toString="0"+rangeNum2toString;}; // just adds a zero before the number if it is only one digit
				rangeString = rangeNum1toString+"-"+rangeNum2toString+": ";
				scoreDisplays[i] = rangeString;
				continue;
			} else if (i==numberOfRanges-1) {
				rangeNum1 = SCORE_END;
				rangeString = "  "+rangeNum1+": "; // no need to check for 0 in last grouping, since 100 will always be 3 digits (and 100 is highest group.
				scoreDisplays[i] = rangeString;
				break;
			} else {
				rangeNum1 = rangeNum2+1;
				rangeNum2 = rangeNum1+SCORE_END/(numberOfRanges-1)-1;
				rangeNum1toString = Integer.toString(rangeNum1);
				rangeNum2toString = Integer.toString(rangeNum2);
				if (rangeNum1toString.length()==1) { rangeNum1toString="0"+rangeNum1toString;}; // just adds a zero before the number if it is only one digit
				if (rangeNum2toString.length()==1) { rangeNum2toString="0"+rangeNum2toString;}; // just adds a zero before the number if it is only one digit
				rangeString = rangeNum1toString+"-"+rangeNum2toString+": ";
				scoreDisplays[i] = rangeString;
			}
		}
		for (int i = 0;i<numberOfRanges;i++) {
			println(scoreDisplays[i]+convertedScores[i]);
		}
	}
	
	private int classifyScore(int score) {
		if (score>99) {
			return 10;
		} else if (score > 89) {
			return 9;
		} else if (score > 79) {
			return 8;
		} else if (score > 69) {
			return 7;
		} else if (score > 59) {
			return 6;
		} else if (score > 49) {
			return 5;
		} else if (score > 39) {
			return 4;
		} else if (score > 29) {
			return 3;
		} else if (score > 19) {
			return 2;
		} else if (score > 9) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private BufferedReader rd;
	private String scoreText;
	private String rangeString;
	private String rangeNum1toString;
	private String rangeNum2toString;
	private int[] scores;
	private String[] scoreDisplays;
	private String[] convertedScores;
	private int score;
	private int rangeNum1;
	private int rangeNum2;
}

