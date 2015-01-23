
import java.util.HashMap;

import acm.program.*;



public class SecAss6Q2 extends ConsoleProgram {
	public void run() {
		nameList = new HashMap<String,Integer>();
		getNames();
		printCounts();
	}
	
	private void getNames() {
		while(true){
			
			String name = readLine("Enter Name: ");
			
			if (name.isEmpty()){
				break;
			}
			
			Integer occurancesOfName = nameList.get(name);
			
			if (occurancesOfName==null){
				nameList.put(name,1);
			} else {
				occurancesOfName++;
				nameList.put(name,occurancesOfName);
			}
		}
	}
	
	private void printCounts() {
		for(String name:nameList.keySet()){
			int occurancesOfName = nameList.get(name);
			println("Entry "+name+" has count "+occurancesOfName);
		}
	}
	
	private HashMap<String,Integer> nameList;
	

}
