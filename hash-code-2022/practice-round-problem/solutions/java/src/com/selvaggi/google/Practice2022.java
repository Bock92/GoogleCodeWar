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
    static Pizzeria pizzeria;

    static int numberOfCustomer = 0;


    /**
     * Read input file
     *
     * @param filePath
     * @param fileName
     * @return ArrayList with the input structured
     */
    private static void readInputFile(String filePath, String fileName) {
        System.out.println("Read file named " + fileName);
        //ArrayList<Pizza> pizzas = new ArrayList<>();

        //streets = new ArrayList<>();


        try {
            File myObj = new File(filePath + fileName);
            System.out.println("File size in bytes " + myObj.length());
            Scanner myReader = new Scanner(myObj);

            numberOfCustomer = myReader.nextInt();



            System.out.println("numberOfCustomer " + numberOfCustomer);

			String test = myReader.nextLine();
			//System.out.println(test);

            boolean likedIngredient = true;
            // Read First Line
			Client c = new Client();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Scanner myLine = new Scanner(data);

                //System.out.println(data);
				int numberOfIngredients = myLine.nextInt();

                while (myLine.hasNext()) {

                    Ingredient newIngredient = new Ingredient(myLine.next());
                    if (likedIngredient) {
						c = new Client();
						c.addLikedIngredient(newIngredient);
					}
                    else {
                        c.addDislikedIngredient(newIngredient);
                        pizzeria.addClient(c);
                    }

                    pizzeria.addIngredient(newIngredient, likedIngredient);
                }
                likedIngredient = !likedIngredient;

                myLine.close();
            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found! " + filePath + fileName);
        }
    }

    private static void writeOutputFile(String outPath, String fileName, World world) {
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
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String filePath = "..\\..\\input_files\\";
        String outPath = "..\\..\\output\\";
        String fileName = "a.txt";

        if (args.length > 0)
            fileName = args[0];

        pizzeria = new Pizzeria();

        // Read the input file
        readInputFile(filePath, fileName);

        System.out.println(pizzeria.toString());


        // Write the file
        //writeOutputFile(outPath, fileName, world);

    }
}
	


