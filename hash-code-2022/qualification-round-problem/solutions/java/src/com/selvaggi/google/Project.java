package com.selvaggi.google;

import java.util.ArrayList;

public class Project {

    public String name;
    public int length;
    public int startDay;
    public int score;
    public int bestBefore;
    public int rolesNumber;
    public ArrayList<Skill> skills;
    public ArrayList<Contributor> contributors;


    public Project(String name, int length, int score, int bestBefore, int rolesNumber){

        this.name = name;
        this.length = length;
        this.score = score;
        this.bestBefore = bestBefore;
        this.rolesNumber = rolesNumber;
        this.skills = new ArrayList<>();
        this.contributors = new ArrayList<>();
        this.startDay = 0;
    }

    public boolean isCompleted(int currentDay){

        return (currentDay - startDay) >= length;
    }

    public String toString(){

        return name + " " + length + " " + score + " " + bestBefore  + " " + rolesNumber  + " " +
                skills.toString()  + " " + contributors.toString();
    }
}
