package com.selvaggi.google;

import java.util.ArrayList;

public class Contributor {
    public String name;
    public Boolean isBusy;
    public ArrayList<Skill> skills;

    public Contributor(String name, Boolean isBusy, ArrayList skills) {
        this.name = name;
        this.isBusy = isBusy;
        this.skills = skills;
    }

    public String toString() {
        return this.name + " isBusy: " + this.isBusy + " " + this.skills.toString(); 
    }


}
