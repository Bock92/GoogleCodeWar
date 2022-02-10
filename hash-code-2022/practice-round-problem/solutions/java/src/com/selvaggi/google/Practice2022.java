package com.selvaggi.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Practice2022 {

	public static final int FLEXIBLE_NUMBER = 0;
	public static final int MIN_CUT = 1100;


	//static ArrayList<Street> streets;


	/**
	 * Read input file
	 * @param filePath
	 * @param fileName
	 * @return ArrayList with the input structured
	 */
	private static void readInputFile(String filePath, String fileName){
		System.out.println("Read file named " + fileName);
		//ArrayList<Pizza> pizzas = new ArrayList<>();

		//streets = new ArrayList<>();


		try {
			File myObj = new File(filePath + fileName);
			System.out.println("File size in bytes " + myObj.length());
			Scanner myReader = new Scanner(myObj);

			// Read First Line
			if(myReader.hasNextLine()){
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);
				/*simulationDuration = myLine.nextInt();
				numberOfIntersection = myLine.nextInt();
				numberOfStreets = myLine.nextInt();
				numberOfCars = myLine.nextInt();
				bonusPoint = myLine.nextInt();*/
				myLine.close();
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
/*
			// Number of teams
			myWriter.write(world.getNIntersection() + "\n");

			for(Intersection intersect: world.intersections) {
				if(intersect.getTotalGreenSeconds() == 0)
					continue;
				myWriter.write(intersect.id + "\n");
				myWriter.write(world.getNStreets(intersect) + "\n");
				// id
				// street => name secondi
				for(Street street: intersect.streets) {
					if(street.greenSeconds > 0) {
						myWriter.write(street.name + " " + street.greenSeconds + "\n");
					}
				}

				myWriter.write("\n");
			}
*/
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


		// Read the input file
		//readInputFile(filePath, fileName);


		// Write the file
		//writeOutputFile(outPath, fileName, world);

	}
}
	


