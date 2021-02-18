package com.selvaggi.google;

import java.util.ArrayList;

public class Pizza {
	
	int numberOfIngredients = 0;
	int index;
	ArrayList<String> ingredients;
	
	public Pizza(int numberOfIngredients){
		this.numberOfIngredients = numberOfIngredients;
		ingredients = new ArrayList<>();
	}
	
	public void addIngredient(String ingredient){
		ingredients.add(ingredient);
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
	
	/**
	 * Return null if not found
	 * @param index
	 * @return
	 */
	public String getIngredient(int index){
		if(index >= ingredients.size() || index < 0)
			return null;
		
		return ingredients.get(index);
	}
	
	public ArrayList<String> getIngredients(){
		return ingredients;
	}
	
	public boolean isIngredientPresent(String ingredient){
		return ingredients.contains(ingredient);
	}
	
	public String toString(){
		String output = "";
		
		for(int i = 0; i < ingredients.size(); i++)
	      output = output + ingredients.get(i) + " ";
		return output;
	}
	
	public static int distinctNumIngredients(Pizza p1, Pizza p2){
		
		int output = 0;
		
		ArrayList<String> totalIngredients =  (ArrayList<String>) p1.getIngredients().clone();
		
		for(int i = 0; i < p2.getIngredients().size(); i++){
			if(!p1.isIngredientPresent(p2.getIngredient(i)))
				totalIngredients.add(p2.getIngredient(i));
		}
		
		return totalIngredients.size();
	}
	

}
