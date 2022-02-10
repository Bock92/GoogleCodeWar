package com.selvaggi.google;

import java.util.ArrayList;

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

    public void addClient(Client c){
        clients.add(c);
    }
}
