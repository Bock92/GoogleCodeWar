package com.selvaggi.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Qualification2021 {

	public static final int FLEXIBLE_NUMBER = 0;
	public static final int MIN_CUT = 1100;

	static int simulationDuration = 0;
	static int numberOfIntersection = 0;
	static int numberOfStreets = 0;
	static int numberOfCars = 0;
	static int bonusPoint = 0;


	static ArrayList<Street> streets;
	static ArrayList<Car> cars;
	static World world;


	/**
	 * Read input file
	 * @param filePath
	 * @param fileName
	 * @return ArrayList with the input structured
	 */
	private static void readInputFile(String filePath, String fileName){
		System.out.println("Read file named " + fileName);
		//ArrayList<Pizza> pizzas = new ArrayList<>();

		streets = new ArrayList<>();
		cars = new ArrayList<>();

		try {
			File myObj = new File(filePath + fileName);
			System.out.println("File size in bytes " + myObj.length());
			Scanner myReader = new Scanner(myObj);

			// Read First Line
			if(myReader.hasNextLine()){
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);
				simulationDuration = myLine.nextInt();
				numberOfIntersection = myLine.nextInt();
				numberOfStreets = myLine.nextInt();
				numberOfCars = myLine.nextInt();
				bonusPoint = myLine.nextInt();
				myLine.close();
			}

			// Read the streets
			for(int i = 0; i < numberOfStreets; i++){
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);

				Street myStreet = new Street();

				myStreet.start_intersection = myLine.nextInt();
				myStreet.end_intersection = myLine.nextInt();
				myStreet.name = myLine.next();
				myStreet.length = myLine.nextInt();
				myStreet.id = i;

				streets.add(myStreet);
				world.addStreet(myStreet);
			}

			// Read the cars
			for(int i = 0; i < numberOfCars; i++){
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);

				Car myCar = new Car();

				myCar.streetsSize = myLine.nextInt();

				ArrayList<Street> path = new ArrayList<>();


				boolean firstStreet = true;
				while(myLine.hasNext()) {
					String newStreet = myLine.next();
					Street street = world.getStreetByName(newStreet);
					path.add(street);
					if(firstStreet){
						street.queueLength++;
						firstStreet = false;
					}
				}

				myCar.streets = path;

				cars.add(myCar);

			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not found! " + filePath + fileName);
		}
	}

	/**
	 * Write Output File
	 * @param outPath
	 * @param finalDeliveries
	 */
	private static void writeOutputFile(String outPath, ArrayList<Delivery> finalDeliveries){
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(outPath + "output.txt");

			// Number of teams
			myWriter.write(finalDeliveries.size() + "");
			for(int i = 0; i < finalDeliveries.size(); i++)
				myWriter.write("\n" + finalDeliveries.get(i).getPizzas() + " " +  finalDeliveries.get(i).toStringIndex());

			myWriter.close();
			System.out.println("Successfully wrote to the file: " + outPath + "output.txt");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String filePath = "..\\..\\input_files\\";
		String outPath = "..\\..\\output\\";
		String fileName = "a.txt";

		if(args.length > 0)
			fileName = args[0];

		world = new World();
		// Read the input file
		readInputFile(filePath, fileName);

		// Print input
		debugPrintInput();





		// Write the file
		//writeOutputFile(outPath, finalDeliveries);

	}


	public static void debugPrintInput(){
		System.out.println("simulationDuration " + simulationDuration+
				" numberOfIntersection " + numberOfIntersection+
				" numberOfStreets " +numberOfStreets+
				" numberOfCars " +numberOfCars+
				" bonusPoint " +bonusPoint );


		for (Street s: streets) {
			System.out.println(s.toString() + " CarsInQueue " + s.queueLength);
		}
		for (Car c: cars) {
			System.out.println(c.toString());
		}
	}
}
	


