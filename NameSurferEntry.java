/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.io.IOException;
import java.util.*;

import acm.util.ErrorException;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		
		ranks = new HashMap<Integer,Integer>();
		
		int start = 0;
		int end = line.indexOf(" ");
		int decadeRank = 0;
		name = line.substring(start, end);
		
		for (int i = 1900;i<=2000;i+=10){
			
			start = end+1;
			
			if(i==2000){
				end=line.length();
			} else {
				end = line.indexOf(" ",start);
			}
			
			decadeRank = Integer.parseInt(line.substring(start,end));

			ranks.put(i,decadeRank);
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int decadeScore = ranks.get(decade);
		return decadeScore;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		
		String returnText = name+" [";
		
		for(int i = 1900; i <= 2000; i+=10){
			returnText += Integer.toString(ranks.get(i));
			returnText += " ";
		}
		
		returnText +="]";
		
		return returnText;
	}
	
	private String name;
	private HashMap<Integer,Integer> ranks;
}

