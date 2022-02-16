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
    static Pizza pizza;
    static Pizza perfectPizza;

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


            // Read First Line

            //while (myReader.hasNextLine()) {
            for (int i = 0; i < numberOfCustomer; i++){
                Client c = new Client(i);


                // Read Like Line
                String likeLine = myReader.nextLine();
                //System.out.println(likeLine);
                Scanner myLine = new Scanner(likeLine);
				int numberOfLikedIngredients = myLine.nextInt();
                while (myLine.hasNext()) {
                    String ingredientName = myLine.next();
                    Ingredient newIngredient = new Ingredient(ingredientName);
                    c.addLikedIngredient(newIngredient);
                    //System.out.println("Like Ingredient: " + ingredientName);
                    pizzeria.addIngredient(newIngredient, true, c);
                    //newIngredient.addLikedClient(c);
                }

                // Read Dislike Line
                String dislikeLine = myReader.nextLine();
                //System.out.println(dislikeLine);
                Scanner myDislikeLine = new Scanner(dislikeLine);
                int numberOfDislikedIngredients = myDislikeLine.nextInt();
                while (myDislikeLine.hasNext()) {
                    String ingredientName = myDislikeLine.next();
                    Ingredient newIngredient = new Ingredient(ingredientName);
                    c.addDislikedIngredient(newIngredient);
                    //System.out.println("Dislike Ingredient: " + ingredientName);
                    pizzeria.addIngredient(newIngredient, false, c);
                    //newIngredient.addDislikedClient(c);
                }

                //System.out.println(c.toString());
                pizzeria.addClient(c);

                myLine.close();
            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found! " + filePath + fileName);
        }
    }

    private static void writeOutputFile(String outPath, String fileName, String outputBody) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(outPath + fileName + "_out.txt");

            myWriter.write(outputBody);

            myWriter.close();
            System.out.println("Successfully wrote to the file: " + outPath + fileName + "_out.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String filePath = "../../input_files/";
        String outPath = "../../output/";
        String fileName = "a.txt";

        if (args.length > 0)
            fileName = args[0];

        pizzeria = new Pizzeria();

        // Read the input file
        readInputFile(filePath, fileName);

        int numberOfIngredients = pizzeria.ingredients.size();
        System.out.println("Number of ingredients: " + numberOfIngredients);

        pizzeria.pizza = pizzeria.cookPerfectPizza();

        System.out.println("Final Pizza Score: " + pizzeria.getScore());

        // Write the file
        writeOutputFile(outPath, fileName, pizzeria.pizza.getOutput());
    }
}
	


