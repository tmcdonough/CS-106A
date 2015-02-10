import java.util.*;

import acm.program.*;


public class FlightPlannerSectionAssign8 extends ConsoleProgram {
	
	public void run() {
		cityList = new Destinations("flights.txt");
		startingCity = getStartCity();
		currentCity = startingCity;
		trip.add(startingCity);
		while(true){
			currentCity = getNextCity();
			trip.add(currentCity);
			if(currentCity.equals(startingCity))break;
		}
		printTrip();
		
	}
	
	private String getStartCity(){
		println("Welcome to Flight Planner!");
		println("Here's a list of all the cities in our database");
		printCities(cityList.getCities());
		println("Let's plan a round-trip route!");
		String readInCity;
		while(true){
			readInCity = readLine("Enter the starting city: ");
			if (cityList.getCities().contains(readInCity))break;
			println("Sorry, we cannot currently fly you from that city. Please enter an acceptable city.");
		}
		return readInCity;
	}
	
	private String getNextCity(){
		println("From "+currentCity+" you can fly directly to: ");
		printCities(cityList.getConnectionCities(currentCity));
		String readInCity;
		while(true){
			readInCity = readLine("Where do you want to go from "+currentCity+"? ");
			if (cityList.getConnectionCities(currentCity).contains(readInCity))break;
			println("Sorry, you cannot get to that city by a direct flight.");
		}
		return readInCity;
	}
	
	private void printCities(ArrayList<String> getCitiesList){
		for (int i = 0; i<getCitiesList.size();i++) println("   "+getCitiesList.get(i));
	}
	
	private void printTrip(){
		String finalPrint = "";
		for (int i = 0; i<trip.size();i++){
			finalPrint+=trip.get(i);
			if(i==trip.size()-1)break;
			finalPrint+=" -> ";
		}
		println("The route you've chosen is:");
		println(finalPrint);
	}
	
	/* instance variables */
	Destinations cityList;
	String startingCity;
	String currentCity;
	ArrayList<String> trip = new ArrayList<String>();

}
