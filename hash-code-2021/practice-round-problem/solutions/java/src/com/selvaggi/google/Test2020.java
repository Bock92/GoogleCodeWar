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

	public static void main(String[] args) {
		
		String filePath = "..\\..\\input_files\\";
		String outPath = "..\\..\\output\\";
	    String fileName = "a_example";
	    
	    int pizzaNumber =0;
	    int twoMemberTeam = 0;
	    int threeMemberTeam = 0;
	    int fourMemberTeam = 0;
	    
	      
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
	            
	            Pizza myPizza = new Pizza(myLine.nextInt());
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
	      //////////////////////////////////////////////////////////
	      /******************* END READ FILE *********************/
	      //////////////////////////////////////////////////////////
	      
	      System.out.println("Number of pizzas: " + pizzas.size());
	      
	      //for(int i = 0; i < pizzas.size(); i++)
	      //  System.out.println(pizzas.get(i).toString());
	      
	      System.out.println("Calcolo 2 combinazioni");
	      
	      // ALL 2 COMBINATION
	      ArrayList<Delivery> deliveries = new ArrayList<>();
	     
	      if(twoMemberTeam != 0){
		      for(int i = 0; i < pizzas.size(); i++){
		    	  for(int j = i + 1; j< pizzas.size(); j++){
			    	  Delivery myDelivery = new Delivery();
			    	  myDelivery.addPizza(pizzas.get(i));
		    		  myDelivery.addPizza(pizzas.get(j));
			    	  deliveries.add(myDelivery);
		    	  }
		      }
	      }
	      
	      System.out.println("Calcolo 3 combinazioni");
	      // ALL 3 COMBINATION
	      if(threeMemberTeam != 0){
		      for(int i = 0; i < pizzas.size(); i++){
		    	  for(int j = i + 1; j< pizzas.size(); j++){
		    		  for(int k = j + 1; k< pizzas.size(); k++){
				    	  Delivery myDelivery = new Delivery();
				    	  myDelivery.addPizza(pizzas.get(i));
			    		  myDelivery.addPizza(pizzas.get(j));
			    		  myDelivery.addPizza(pizzas.get(k));
				    	  deliveries.add(myDelivery);
		    	      }
		          }
		      }
	      }
	      
  
	      System.out.println("Calcolo 4 combinazioni");
	      // ALL 4 COMBINATION
	      if(fourMemberTeam != 0){
		      for(int i = 0; i < pizzas.size(); i++){
		    	  for(int j = i + 1; j< pizzas.size(); j++){
		    		  for(int k = j + 1; k< pizzas.size(); k++){
		    			  for(int z = k + 1; z < pizzas.size(); z++){
					    	  Delivery myDelivery = new Delivery();
					    	  myDelivery.addPizza(pizzas.get(i));
				    		  myDelivery.addPizza(pizzas.get(j));
				    		  myDelivery.addPizza(pizzas.get(k));
				    		  myDelivery.addPizza(pizzas.get(z));
					    	  deliveries.add(myDelivery);
			    	      }
			          }
			      }
		      }
	      }
	      

	      
	      Collections.sort(deliveries, new Comparator<Delivery>() {
	          @Override
	          public int compare(Delivery d2, Delivery d1)
	          {
	              return  d1.getScore() - d2.getScore();
	          }
	      });
	    	
//	      for(int i = 0; i < deliveries.size(); i++)
//		        System.out.println(deliveries.get(i).getPizzas() + " | " + deliveries.get(i).getScore() + " | " + deliveries.get(i).toStringIndex());
	      
	      ArrayList<Delivery> finalDeliveries = new ArrayList<>();
	      
	      //Get first Delivery
	      finalDeliveries.add(deliveries.get(0));
	      ArrayList<Pizza> pizzaDelivered = new ArrayList<>();
	      
	      for (Pizza p: finalDeliveries.get(0).pizzas){
	    	  pizzaDelivered.add(p);
	      }
	      
	      
	      pizzaNumber -= deliveries.get(0).getPizzas();
	      
	      
	      
	      
	      for (Delivery d : deliveries) {
	    	  if(d.getPizzas() > pizzaNumber)
	    		  continue;
	    	  
	    	  boolean contained = false;
	    	  for(Pizza p: d.pizzas){
	    		  for(Pizza dp: pizzaDelivered){
	    			  if(p.getIndex() == dp.getIndex())
	    				  contained = true;
	    		  }
	    	  }
	    	  if(contained)
	    		  continue;
	    	  
	    	  finalDeliveries.add(d);	    		  
		 }
	      
	      
	      
	      // FINAL OUTPUT
	      int finalScore = 0;
	      for(int i = 0; i < finalDeliveries.size(); i++)
	    	  finalScore += Math.pow(finalDeliveries.get(i).getScore(),2);
	      System.out.println("Final Score: " + finalScore);
	      
	      System.out.println("FINAL OUTPUT");
	      System.out.println(finalDeliveries.size());
	      for(int i = 0; i < finalDeliveries.size(); i++)
		        System.out.println(finalDeliveries.get(i).getPizzas() + " " +  finalDeliveries.get(i).toStringIndex());

			// Write the file
		FileWriter myWriter = null;
			try {
				myWriter = new FileWriter(outPath + "output.txt");

				// Number of teams
				myWriter.write(finalDeliveries.size() + "");
				for(int i = 0; i < finalDeliveries.size(); i++)
					myWriter.write("\n" + finalDeliveries.get(i).getPizzas() + " " +  finalDeliveries.get(i).toStringIndex());

				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}
	


