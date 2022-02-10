package com.selvaggi.google;

public class Ingredient {

    public String name;
    public int liked_clients;
    public int disliked_clients;
    public double score;

    public Ingredient(String ingredientName){
        name = ingredientName;
        liked_clients = 0;
        disliked_clients = 0;
    }

    public void addLike(){
        liked_clients++;
    }

    public void addDislike(){ disliked_clients++; }

    public double getScore(){
        score = liked_clients * 0.4 - disliked_clients * 0.6;
        return score;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Ingredient){
            Ingredient ptr = (Ingredient) v;
            retVal = ptr.name.equals(this.name);
        }

        return retVal;
    }

    public String toString(){
        return name + " Like: " + liked_clients + " Dislike: " + disliked_clients + " Score: " + getScore();
    }
}
