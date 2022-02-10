package com.selvaggi.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Pizzeria {
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Client> clients;
    public Pizza pizza;

    public Pizzeria(){
        ingredients = new ArrayList<>();
        clients = new ArrayList<>();
        pizza = new Pizza();
    }

    public void addIngredient(Ingredient i, boolean liked){
        if(!ingredients.contains(i)){
            if(liked)
                i.addLike();
            else
                i.addDislike();
            ingredients.add(i);
        }
        else {
            for (Ingredient j: ingredients) {
                if(j.equals(i)){
                    if(liked)
                        j.addLike();
                    else
                        j.addDislike();
                    break;
                }
            }
        }
    }

    public void addClient(Client c) {
        clients.add(c);
    }

    /**
    * Crea la pizza.
    * @param minScore Il punteggio minimo degli ingredienti della pizza
     * @return La pizza da consegnare
     */
    public Pizza cookPizza(int minScore){

        // Ordina gli ingredienti per punteggio.
        sortIngredientsByScore();

        // Crea la pizza.
        for (Ingredient i: ingredients) {

            if(i.getScore() >= minScore)
                pizza.ingredients.add(i);
        }

        return pizza;
    }

    /**
     * Ordina gli ingredienti per punteggio.
     */
    public void sortIngredientsByScore(){

        Collections.sort(ingredients, new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient i, Ingredient j) {
                if (i.getScore() > j.getScore())
                    return 1;
                if (i.getScore() < j.getScore())
                    return -1;
                return 0;
            }
        });
    }


    public String toString(){

        String output = "Clients:\n";
        for (Client c: clients             ) {
            output = output + c.toString() + "\n";
        }
        output = output +"\nIngredients:\n";
        for (Ingredient i: ingredients             ) {
            output = output + i.toString() + "\n";
        }
        return output;
    }
}
