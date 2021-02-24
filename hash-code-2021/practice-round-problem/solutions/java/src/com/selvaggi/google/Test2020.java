package com.selvaggi.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Test2020 {

	public static final int FLEXIBLE_NUMBER = 0;
	public static final int MIN_CUT = 1000;

	static int pizzaNumber =0;
	static int twoMemberTeam = 0;
	static int threeMemberTeam = 0;
	static int fourMemberTeam = 0;
	static int maxNumOfIngredient = 0;

	/**
	 * Read input file
	 * @param filePath
	 * @param fileName
	 * @return ArrayList with the input structured
	 */
	private static ArrayList<Pizza> readInputFile(String filePath, String fileName){
		System.out.println("Read file named " + fileName);
		ArrayList<Pizza> pizzas = new ArrayList<>();

		try {
			File myObj = new File(filePath + fileName);
			System.out.println("File size in bytes " + myObj.length());
			Scanner myReader = new Scanner(myObj);

			// Read First Line
			if(myReader.hasNextLine()){
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);
				pizzaNumber = myLine.nextInt();
				twoMemberTeam = myLine.nextInt();
				threeMemberTeam = myLine.nextInt();
				fourMemberTeam = myLine.nextInt();
				myLine.close();
			}


			// Loop through the lines
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				Scanner myLine = new Scanner(data);

				// Read number of ingredients
				int numOfIngredient = myLine.nextInt();
				if(numOfIngredient > maxNumOfIngredient)
					maxNumOfIngredient = numOfIngredient;

				Pizza myPizza = new Pizza(numOfIngredient);
				myPizza.setIndex(pizzas.size());

				// Loop through the tokens
				while(myLine.hasNext())
					myPizza.addIngredient(myLine.next());
				myLine.close();
				pizzas.add(myPizza);
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not found! " + filePath + fileName);
		}
		return pizzas;
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
		String fileName = "a_example";

		if(args.length > 0)
			fileName = args[0];

		// Read the input file
		ArrayList<Pizza> pizzas = readInputFile(filePath, fileName);

		// Print file statistics
		System.out.println("Team 2: " + twoMemberTeam + " Team 3: "+ threeMemberTeam + " Team 4: " + fourMemberTeam);


		//////////////////////////////////////////////////////////
		/******************* END READ FILE *********************/
		//////////////////////////////////////////////////////////

		// Sort pizzas by ingredients
		Collections.sort(pizzas, new Comparator<Pizza>() {
			@Override
			public int compare(Pizza p2, Pizza p1)
			{
				return  p1.numberOfIngredients - p2.numberOfIngredients;
			}
		});


		// First Cut
		int firstCut = Math.min(twoMemberTeam * 2 + threeMemberTeam  * 3 + fourMemberTeam * 4 + FLEXIBLE_NUMBER, MIN_CUT); //504 539 585

		System.out.println("First cut " + firstCut);

		for (int i = pizzas.size()-1; i >= firstCut; i--)
			pizzas.remove(i);

		pizzaNumber = pizzas.size();

		System.out.println("Number of pizzas: " + pizzas.size() + " Max Ingredients: " + maxNumOfIngredient);

		System.out.println("Working on 2 combinations");

		// ALL 2 COMBINATION
		ArrayList<Delivery> twoDeliveries = new ArrayList<>();

		if(twoMemberTeam != 0){
			for(int i = 0; i < pizzas.size(); i++){
				for(int j = i + 1; j< pizzas.size(); j++){
					Delivery myDelivery = new Delivery();
					myDelivery.addPizza(pizzas.get(i));
					myDelivery.addPizza(pizzas.get(j));
					twoDeliveries.add(myDelivery);
				}
			}
		}

		Collections.sort(twoDeliveries, new Comparator<Delivery>() {
			@Override
			public int compare(Delivery d2, Delivery d1)
			{
				return  d1.getScore() - d2.getScore();
			}
		});



		System.out.println("Working on 3 combinations");
		// SECOND CUT
		int secondCut = Math.min(Math.max(threeMemberTeam, fourMemberTeam) + FLEXIBLE_NUMBER, twoDeliveries.size());
		ArrayList<Delivery> threeDeliveries = new ArrayList<>();

		if(threeMemberTeam != 0) {
			for(int i = 0; i < secondCut; i++){
				for(int j = 0; j < pizzas.size(); j++){
					if(!twoDeliveries.get(i).containsPizza(pizzas.get(j))){
						Delivery myDelivery = new Delivery();
						// Copy deliver
						for(Pizza p: twoDeliveries.get(i).pizzas) {
							myDelivery.addPizza(p);
						}
						myDelivery.addPizza(pizzas.get(j));
						threeDeliveries.add(myDelivery);
					}
				}
			}
		}

		Collections.sort(threeDeliveries, new Comparator<Delivery>() {
			@Override
			public int compare(Delivery d2, Delivery d1)
			{
				return  d1.getScore() - d2.getScore();
			}
		});



		// ALL 3 COMBINATION
		System.out.println("Working on 4 combinations");
		// THIRD CUT
		int thirdCut = Math.min(fourMemberTeam + FLEXIBLE_NUMBER, threeDeliveries.size());
		ArrayList<Delivery> fourDeliveries = new ArrayList<>();

		if(fourMemberTeam != 0) {
			for(int i = 0; i < thirdCut; i++){
				for(int j = 0; j < pizzas.size(); j++){
					if(!threeDeliveries.get(i).containsPizza(pizzas.get(j))){
						Delivery myDelivery = new Delivery();
						// Copy deliver
						for(Pizza p: threeDeliveries.get(i).pizzas) {
							myDelivery.addPizza(p);
						}
						myDelivery.addPizza(pizzas.get(j));
						fourDeliveries.add(myDelivery);
					}
				}
			}
		}

		Collections.sort(fourDeliveries, new Comparator<Delivery>() {
			@Override
			public int compare(Delivery d2, Delivery d1)
			{
				return  d1.getScore() - d2.getScore();
			}
		});


		/// MIX 2 + 3 +4;
		ArrayList<Delivery> deliveries = new ArrayList<>();

		for(Delivery d: fourDeliveries)
			deliveries.add(d);
		for(Delivery d: threeDeliveries)
			deliveries.add(d);
		for(Delivery d: twoDeliveries)
			deliveries.add(d);

		Collections.sort(deliveries, new Comparator<Delivery>() {
			@Override
			public int compare(Delivery d2, Delivery d1)
			{
				return  d1.getScore() - d2.getScore();
			}
		});


		ArrayList<Delivery> finalDeliveries = new ArrayList<>();

		//Get first Delivery
		finalDeliveries.add(deliveries.get(0));
		ArrayList<Pizza> pizzaDelivered = new ArrayList<>();

		for (Pizza p: finalDeliveries.get(0).pizzas){
			pizzaDelivered.add(p);
		}


		pizzaNumber -= deliveries.get(0).getPizzas();

		for (Delivery d : deliveries) {
			if(twoMemberTeam <= 0 && d.pizzas.size() == 2 || threeMemberTeam <= 0 && d.pizzas.size() == 3 || fourMemberTeam <= 0 && d.pizzas.size() == 4)
				continue;

			if(d.getPizzas() > pizzaNumber)
				continue;

			boolean contained = false;

			for(Pizza p: pizzaDelivered){
				if(d.containsPizza(p)) {
					contained = true;
					break;
				}
			}

			if(!contained) {

				if(d.pizzas.size() == 2)
					twoMemberTeam--;
				if(d.pizzas.size() == 3)
					threeMemberTeam--;
				if(d.pizzas.size() == 4)
					fourMemberTeam--;

				finalDeliveries.add(d);
				for(Pizza p: d.pizzas)
					pizzaDelivered.add(p);
			}
		}



		// FINAL OUTPUT
		System.out.println("FINAL OUTPUT");
		int finalScore = 0;
		for(int i = 0; i < finalDeliveries.size(); i++)
			finalScore += Math.pow(finalDeliveries.get(i).getScore(),2);
		System.out.println("Final Score: " + finalScore);
		System.out.println("Number of deliveries: " + finalDeliveries.size());

		// Write the file
		writeOutputFile(outPath, finalDeliveries);

	}
}
	


