import java.io.*;
import java.util.*;

import acm.program.*;
import acm.util.ErrorException;


public class Destinations extends ConsoleProgram {
	
	public Destinations(String filename){
		connections = new HashMap<String,ArrayList>();
		try {
			rd = new BufferedReader(new FileReader(filename));
			while (true){
				String line = rd.readLine();
				if (line==null) break;
				String city1 = ParseLine(line)[0];
				String city2 = ParseLine(line)[1];
				if (connections.get(city1)!=null && connections.get(city1).contains(city2)==false){
					connections.get(city1).add(city2);
				} else if (connections.get(city1)==null){
					ArrayList<String> connectingCities = new ArrayList<String>();
					connectingCities.add(city2);
					connections.put(city1,connectingCities);
				}
			}
		} catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
	private String[] ParseLine(String line){
		int center = line.indexOf(" -> ");
		String city1 = line.substring(0, center);
		String city2 = line.substring(center+" -> ".length(),line.length());
		String[] pair = {city1,city2};
		return pair;
	}
	
	public ArrayList<String> getCities(){
		Iterator<String> cityIt = connections.keySet().iterator();
		ArrayList<String> cities = new ArrayList<String>();
		while(cityIt.hasNext()){
			cities.add(cityIt.next());
		}
		return cities;
	}
	
	public void printCities(){
		Iterator<String> cityIt = connections.keySet().iterator();
		while(cityIt.hasNext()){
			println(cityIt.next());
		}
	}
	
	public void printConnectionCities(String currentCity){
		ArrayList<String> cities = connections.get(currentCity);
		Iterator<String> cityIt = cities.iterator();
		while(cityIt.hasNext()){
			println(cityIt.next());
		}
	}
	
	public ArrayList<String> getConnectionCities(String currentCity){
		return connections.get(currentCity);
	}
	
	/* ivars */
	private HashMap<String,ArrayList> connections;
	private BufferedReader rd;
}
