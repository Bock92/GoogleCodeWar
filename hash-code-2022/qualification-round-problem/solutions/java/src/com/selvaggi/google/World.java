package com.selvaggi.google;

import java.util.*;

public class World {

    public int currentDay;
    public ArrayList<Project> projects;
    public ArrayList<Contributor> contributors;

    public World() {
        this.projects = new ArrayList<>();
        this.contributors = new ArrayList<>();
    }

    public void elaborate(){

        while (true){

            if(currentDay > 2)
                break;

            // Giorno successivo
            currentDay++;
        }
    }


    public double getScore(){
        return 0;
    }


    public String getOutput(){
        return "";
    }


    public String toString(){

        return currentDay + " " + projects.toString() + " " + contributors.toString();
    }
}
