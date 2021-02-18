package com.selvaggi.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Delivery {
	
	public ArrayList<Pizza> pizzas;
	int score;

	public Delivery() {
		pizzas = new ArrayList<>();
	}
	
	public void addPizza(Pizza pizza){
		pizzas.add(pizza);
	}
	
	public int getPizzas(){
		return pizzas.size();
	}
	
	public boolean containsPizza(Pizza pizza){
		
		for (Pizza p : pizzas) {
			if(p.getIndex() == pizza.getIndex())
				return true;
		}
		
		return false;
	}

	public int getScore(){
		 Set<String> totalIngredients = new HashSet<String>(); 
		 
		 for (Pizza p : pizzas) {
			 for (String i : p.getIngredients()) {
				 totalIngredients.add(i);
			}
		}
		
		return totalIngredients.size();
	}
	
	public String toString(){
		String output = "| ";
		
		for(int i = 0; i < pizzas.size(); i++)
	      output = output + pizzas.get(i).toString() + "| ";
		return output;
	}
	
	public String toStringIndex(){
		String output = "";
		
		for(int i = 0; i < pizzas.size(); i++)
	      output = output + pizzas.get(i).getIndex() + " ";
		return output;
	}

}
