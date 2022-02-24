package com.selvaggi.google;

public class Skill {
    public String name;
    public int level;

    public Skill(String name, int level){
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Skill){
            Skill ptr = (Skill) v;
            retVal = ptr.name.equals(this.name) && ptr.level >= this.level;
        }

        return retVal;
    }

    public String toString(){
        return "Skill Name: " +  this.name  + " Level: " + level;
    }
}
