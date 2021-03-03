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
	static ArrayList<Intersection> intersections;
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
		intersections = new ArrayList<>();

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

		   // Generate Intersections
			for(int i = 0; i < numberOfIntersection; i++){
				Intersection myIntersection = new Intersection();
				myIntersection.id = i;
				intersections.add(myIntersection);
			}
			// Link the world to the local object
			world.intersections = intersections;

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

				world.addStreet(myStreet);
				streets.add(myStreet);
				intersections.get(myStreet.end_intersection).streets.add(myStreet);
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

	private static void writeOutputFile(String outPath, String fileName, World world){
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(outPath + fileName + "_out.txt");

			// Number of teams
			myWriter.write(world.getNIntersection() + "\n");

			for(Intersection intersect: world.intersections) {
<<<<<<< Updated upstream

=======
				if(intersect.getTotalGreenSeconds() == 0)
					continue;
>>>>>>> Stashed changes
				myWriter.write(intersect.id + "\n");
				myWriter.write(world.getNStreets(intersect) + "\n");
				// id
				// street => name secondi
				for(Street street: intersect.streets) {
					if(street.greenSeconds > 0) {
<<<<<<< Updated upstream
						myWriter.write("\n" + street.name + " " + street.greenSeconds);
=======
						myWriter.write(street.name + " " + street.greenSeconds + "\n");
>>>>>>> Stashed changes
					}
				}

				myWriter.write("\n");
			}

			myWriter.close();
			System.out.println("Successfully wrote to the file: " + outPath + fileName + "_out.txt");
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
		//debugPrintInput();
		//printIntersection();

		world.cars = cars;

		for(int i =0 ; i < simulationDuration; i++){
			world.updateTrafficLight();
			world.update();
		}


		// Write the file
		writeOutputFile(outPath, fileName, world);

	}

	public static void printIntersection(){
		for(Intersection i: world.intersections){
			System.out.print("Intersection " + i.id);
			for(Street s: i.streets)
				System.out.print(" " + s.name);
			System.out.println("");
		}
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
	


