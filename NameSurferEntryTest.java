
import java.util.HashMap;

import acm.program.*;


public class NameSurferEntryTest extends ConsoleProgram implements NameSurferConstants {
	
	public void run() {	
		
		//String line = "A 83 140 228 286 426 612 486 577 836 0 0";
		String line = "Aaliyah 0 0 0 0 0 0 0 0 0 380 215";
		
		NameSurferEntry entry = new NameSurferEntry(line);
		println(entry.getName());
		println(entry.getRank(1900));
		println(entry.toString());
//		String name = line.substring(0, line.indexOf(" "));
//		String last = name;
//		int decade = 1900;
//		int decadeRank = 0;
//		println(name);
//		for (int i = decade;i<=2000;i+=10){
//			if (i!=2000){
//				last = line.substring(line.indexOf(" ",line.indexOf(last))+1, line.indexOf(" ",line.indexOf(last)+last.length()+1));
//				decadeRank = Integer.parseInt(last);
//			} else {
//				last = line.substring(line.indexOf(" ",line.indexOf(last))+1);
//				decadeRank = Integer.parseInt(last);
//			}
//			//ranks.put(i,decadeRank);
//			println(i);
//			println(decadeRank);
		
//		int start = 0;
//		int end = line.indexOf(" ");
//		println(end);
//		int decadeRank = 0;
//		String name = line.substring(start, end);
//		println(name);
//		for (int i = 1900;i<=2000;i+=10){
//			
//			start = end+1;
//			println(start);
//			
//			if(i==2000){
//				end=line.length();
//			} else {
//				end = line.indexOf(" ",start);
//			}
//			
//			println(end);
//			decadeRank = Integer.parseInt(line.substring(start,end));
//			println(decadeRank);
//
//	
	}
	}
