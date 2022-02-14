package com.selvaggi.google;

import java.util.ArrayList;

public class Ingredient {

    public String name;
    public int liked_clients;
    public int disliked_clients;
    public double score;

    public ArrayList<Client> likedClients;
    public ArrayList<Client> dislikedClients;

    public Ingredient(String ingredientName){
        name = ingredientName;
        liked_clients = 0;
        disliked_clients = 0;
        likedClients = new ArrayList<>();
        dislikedClients = new ArrayList<>();
    }

    public void addLikedClient(Client c){
        likedClients.add(c);
    }

    public void addDislikedClient(Client c){
        dislikedClients.add(c);
    }

    public boolean removeLikeClient(Client clientToRemove){
                return likedClients.remove(clientToRemove);
    }

    public boolean removeDislikeClient(Client clientToRemove){
        return dislikedClients.remove(clientToRemove);
    }

    public void addLike(){
        liked_clients++;
    }

    public void addDislike(){ disliked_clients++; }

    public double getScore(double likeBalance){
        score = liked_clients * likeBalance - disliked_clients * (1 - likeBalance);
        return score;
    }

    public double getScore(){
        return likedClients.size() - dislikedClients.size();
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
        return name + " Like: " + likedClients.size() + " Dislike: " + dislikedClients.size() + " Score: " + getScore();
    }


    public String toStringClients(){
        String likedClientsString = "";
        for (Client c: likedClients)
            likedClientsString = likedClientsString + " "+ c.clientId;

        String dislikedClientsString = "";
        for (Client c: dislikedClients)
            dislikedClientsString = dislikedClientsString + " "+ c.clientId;

        return name +" | Like List: " + likedClientsString + " | Dislikes List: " + dislikedClientsString;
    }
}
