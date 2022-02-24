package com.selvaggi.google;

import java.util.ArrayList;

public class Contributor {
    public String name;
    public boolean isBusy;
    public boolean isUnderEvaluation;
    public ArrayList<Skill> skills;

    public Contributor(String name, boolean isBusy) {
        this.name = name;
        this.isBusy = isBusy;
        this.skills = new ArrayList<>();
        this.isUnderEvaluation = false;
    }

    public String toString() {
        return this.name + " isBusy: " + this.isBusy + " " + this.skills.toString();
    }

}
