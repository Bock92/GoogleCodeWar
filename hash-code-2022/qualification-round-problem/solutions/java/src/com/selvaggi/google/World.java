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
        this.completedProjects = new ArrayList<>();
    }

    public void elaborate(){

        int pIdx = 0;

        // Sort Projects

        while (true){

            ArrayList<Contributor> tmpContributors = new ArrayList<>();

            Project currentProject = projects.get(pIdx);


            // Giorno successivo
            currentDay++;


            if(currentDay > 2)
                break;
        }
    }

    public ArrayList<Contributor>  isProjectWorkable(Project project){

        ArrayList<Contributor> tmpContr = new ArrayList<>();

        for (Skill s:project.skills ) {

            //find contributor
            Contributor c = findContributor(s);
            tmpContr.add(c);
            if(c == null) {
                break;
            }
        }

        // Reset flag
        for (Contributor c: contributors ) {
            c.isUnderEvaluation = false;
        }

        if(project.skills.size() == tmpContr.size())
            return tmpContr;

        return null;
    }

    public Contributor findContributor(Skill skill){

        for(Contributor c: contributors){
            if(c.isBusy)
                continue;
            if(c.isUnderEvaluation)
                continue;
            if(c.skills.contains(skill)) {
                c.isUnderEvaluation = true;
                return c;
            }
        }

        return null;
    }


    public double getScore(){
        return 0;
    }


    public String getOutput(){
        return "";
    }


    public String toString(){

        return currentDay + " \n" + projects.toString() + " \n" + contributors.toString();
    }
}
