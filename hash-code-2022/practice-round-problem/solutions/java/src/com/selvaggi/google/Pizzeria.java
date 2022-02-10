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
}
