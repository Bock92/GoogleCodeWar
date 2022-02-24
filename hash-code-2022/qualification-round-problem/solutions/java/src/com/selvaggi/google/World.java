package com.selvaggi.google;

import java.util.*;

public class World {

    public int currentDay;
    public ArrayList<Project> projects;
    public ArrayList<Contributor> contributors;
    public ArrayList<Project> completedProjects;

    public World() {
        this.projects = new ArrayList<>();
        this.contributors = new ArrayList<>();
    }

    public void elaborate(){

        int pIdx = 0;

        while (true){

            ArrayList<Contributor> tmpContributors = new ArrayList<>();

            Project currentProject = projects.get(pIdx);
            for (Skill s:currentProject.skills ) {

                //if(contributors.contains(s))
                    //currentProject.contributors.add()

            }

            // Giorno successivo
            currentDay++;


            if(currentDay > 2)
                break;
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
