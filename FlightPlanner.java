package week5;

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;
public class FlightPlanner extends ConsoleProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void run() {
		println("Welcome to Flight Planner!");
		readFlightData("flights.txt");
		println("Here's a list of all the cities in our database:");
		printCityList(cities);
		println("Let's plan a round-trip route!");
		String startCity = readLine("Enter the starting city: ");
		ArrayList<String> route = new ArrayList<String>();
		route.add(startCity);
		String currentCity = startCity;
		while (true) {
			String nextCity = getNextCity(currentCity);
			route.add(nextCity);
			if (nextCity.equals(startCity)) break;
			currentCity = nextCity;
		}
		printRoute(route);
	}
	/** Ask user for the name of the next city in the route */
	private String getNextCity(String city) {
		ArrayList<String> destinations = getDestinations(city);
		String nextCity = null;
		while (true) {
			println("From " + city + " you can fly directly to:");
			printCityList(destinations);
			String prompt = "Where do you want to go from " + city + "? ";
			nextCity = readLine(prompt);
			if (destinations.contains(nextCity)) break;
			println("You can't get to that city by a direct flight.");
		}
		return nextCity;
	}


	private ArrayList<String> getDestinations(String fromCity) {
		return flights.get(fromCity);
	}


	private void printCityList(ArrayList<String> cityList) {
		for(int i = 0; i < cityList.size(); i++) {
			String city = cityList.get(i);
			println(" " + city);
		}
	}


	private void printRoute(ArrayList<String> route) {
		println("The route you've chosen is: ");
		for (int i = 0; i < route.size(); i++) {
			if (i > 0) print(" -> ");
			print(route.get(i));
		}
		println();
	}


	private void readFlightData(String filename) {
		flights = new HashMap<String, ArrayList<String>>();
		cities = new ArrayList<String>();
		try {
			BufferedReader rd =
					new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				if (line.length() != 0) {
					readFlightEntry(line);
				}
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	private void readFlightEntry(String line) {
		String[] cities = line.split("->");
		if (cities.length != 2) {
			throw new ErrorException("Illegal flight entry " + line);
		}

		String fromCity = cities[0].trim();
		String toCity = cities[1].trim();
		defineCity(fromCity);
		defineCity(toCity);
		getDestinations(fromCity).add(toCity);
	}

	private void defineCity(String cityName) {
		if (!cities.contains(cityName)) {
			cities.add(cityName);
			flights.put(cityName, new ArrayList<String>());
		}
	}
	/* Private instance variables */
	private Map<String, ArrayList<String>> flights;
	private ArrayList<String> cities;
}