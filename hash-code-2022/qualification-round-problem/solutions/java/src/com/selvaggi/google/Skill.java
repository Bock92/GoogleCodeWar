package com.selvaggi.google;

public class Skill {
    public String name;
    public int level;

    public Skill(String name, int level){
        this.name = name;
        this.level = level;
    }

    public String toString(){
        return "Skill Name: " +  this.name  + " Level: " + level;
    }
}
