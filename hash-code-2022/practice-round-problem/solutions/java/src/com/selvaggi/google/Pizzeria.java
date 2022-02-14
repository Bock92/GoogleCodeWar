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

    public void addIngredient(Ingredient i, boolean liked, Client c){

        if(!containsIngredient(i.name)){
            if(liked) {
                i.addLike();
                i.addLikedClient(c);
            }
            else {
                i.addDislike();
                i.addDislikedClient(c);
            }
            ingredients.add(i);
        }
        else {
            for (Ingredient j: ingredients) {
                if(j.equals(i)){
                    if(liked) {
                        j.addLike();
                        j.addLikedClient(c);
                    }
                    else {
                        j.addDislike();
                        j.addDislikedClient(c);
                    }
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

        // Reset pizza;
        pizza = new Pizza();

        // Ordina gli ingredienti per punteggio.
        sortIngredientsByScore();

        // Crea la pizza.
        for (Ingredient i: ingredients) {

            if(i.getScore() >= minScore)
                pizza.ingredients.add(i);
        }

        return pizza;
    }

    public Pizza cookPerfectPizza(){

        // Reset pizza;
        pizza = new Pizza();

        // Copy ingredients
        ArrayList<Ingredient> tmpIngredients = new ArrayList<>();
        tmpIngredients.addAll(ingredients);


        // Ordina gli ingredienti per punteggio.
        sortIngredientsByScoreDesc(tmpIngredients);

        Ingredient ingredientToAdd = tmpIngredients.get(0);
        pizza.ingredients.add(ingredientToAdd);

        System.out.println(toStringIngredients(tmpIngredients));
        // Removed clients
        removeIngredient(tmpIngredients, ingredientToAdd);

        System.out.println(toStringIngredients(tmpIngredients));


        return pizza;
    }

    public void removeIngredient(ArrayList<Ingredient> ingredientList, Ingredient ingredientToRemove){

   /*     // Remove all liked customers
        for (Client c: ingredientToRemove.likedClients) {
            for (Ingredient i: ingredientList) {
                i.likedClients.remove(c);
            }
        }*/
 /*       // Remove all disliked customers
        for (Client c: ingredientToRemove.dislikedClients) {
            for (Ingredient i: ingredientList) {
                i.dislikedClients.remove(c);
            }
        }
*/
        ingredientList.remove(ingredientToRemove);
    }

    public static void sortIngredientsByScoreDesc(ArrayList<Ingredient> ingredientList){
        Collections.sort(ingredientList, new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient i, Ingredient j) {
                if (i.getScore() < j.getScore())
                    return 1;
                if (i.getScore() > j.getScore())
                    return -1;
                return 0;
            }
        });
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

    /**
     * Verifica se l'ingrediente è già stato aggiunto
     * @param name Il nome dell'ingrediente
     * @return true se è contenuto, false altrimenti
     */
    private boolean containsIngredient(String name){

        for(int i = 0; i < ingredients.size(); i++){

            if(ingredients.get(i).name.equals(name)){

                return true;
            }
        }

        return false;
    }

    public int getScore(){

        int score = 0;

        for (Client c: clients) {

            boolean skipClient = false;
            for(int i = 0; i < c.dislikedIngredients.size(); i++){

                if(pizza.ingredients.contains(c.dislikedIngredients.get(i)))
                    skipClient = true;
            }

            if(skipClient)
                continue;
            else {

                boolean allLiked = false;

                for(int i = 0; i < c.likedIngredients.size(); i++){

                    if(pizza.ingredients.contains(c.likedIngredients.get(i)))
                        allLiked = true;
                    else {

                        allLiked = false;
                        break;
                    }
                }

                if(allLiked)
                    score++;
            }
        }

        return score;
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

    public String toStringIngredients(ArrayList<Ingredient> ingredientList){
        String output = "Ingredients:\n";
        for (Ingredient i: ingredientList) {
            output = output + i.toString() + "\n";
        }
        return output;
    }
}
