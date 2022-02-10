package com.selvaggi.google;

import java.util.ArrayList;

public class Client {

    public ArrayList<Ingredient> likedIngredients;
    public ArrayList<Ingredient> dislikedIngredients;

    Client(){

        likedIngredients = new ArrayList<>();
        dislikedIngredients = new ArrayList<>();
    }

    public void addLikedIngredient(Ingredient i){
        likedIngredients.add(i);
    }

    public void addDislikedIngredient(Ingredient i){
        dislikedIngredients.add(i);
    }

    /**
    * Restituisce 1 se l'ingrediente è piaciuto, -1 se non è piaciuto, 0 se non è neutro.
     * @param ingredient L'ingrediente di cui si vuole sapere il punteggio
     * @return Il punteggio dell'ingrediente
     */
    public int getIngredientScore(Ingredient ingredient){

        if(likedIngredients.contains(ingredient))
            return 1;
        else if(dislikedIngredients.contains(ingredient))
            return -1;
        else
            return 0;
    }

    public String toString(){
        return "Liked ingredients: " + likedIngredients.size() + " Disliked Ingredients: " + dislikedIngredients.size();
    }
}
