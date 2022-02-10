package com.selvaggi.google;

import java.util.ArrayList;

public class Client {

    public ArrayList<Ingredient> likedIngredients;
    public ArrayList<Ingredient> dislikedIngredients;

    Client(){

        likedIngredients = new ArrayList<>();
        dislikedIngredients = new ArrayList<>();
    }

    /*
    Restituisce 1 se l'ingrediente è piaciuto, -1 se non è piaciuto, 0 se non è neutro.
     */
    public int getIngredientScore(Ingredient ingredient){

        if(likedIngredients.contains(ingredient))
            return 1;
        else if(dislikedIngredients.contains(ingredient))
            return -1;
        else
            return 0;
    }
}
