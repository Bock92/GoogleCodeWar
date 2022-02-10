package com.selvaggi.google;

public class Ingredient {

    public String name;
    public int liked_clients;
    public int disliked_clients;
    public int score;

    public Ingredient(String ingredientName){
        name = ingredientName;
        liked_clients = 0;
        disliked_clients = 0;
    }

    public void addLike(){
        liked_clients++;
    }

    public void addDislike(){
        disliked_clients++;
    }

    public int getScore(){
        score = liked_clients - disliked_clients;
        return score;
    }

    public boolean equals(Ingredient i){
        return i.name.equals(this.name);
    }

}
