package com.selvaggi.google;

import java.util.ArrayList;

public class Pizza {

    public ArrayList<Ingredient> ingredients;

    Pizza(){

        ingredients = new ArrayList<>();
    }

    public String toString() {
        String output = "Pizza size: " + ingredients.size() + "\n";
        for (Ingredient i: ingredients) {
            output = output + i.toString() + "\n";
        }
        return  output;
    }

    public String getOutput(){
        String output =  ingredients.size() + "";

        for (Ingredient i: ingredients)
            output = output + " " + i.name;

        return output;
    }
}
