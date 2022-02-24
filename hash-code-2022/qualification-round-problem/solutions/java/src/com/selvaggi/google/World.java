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

            // Check complete projects



            Project currentProject = projects.get(pIdx);

            ArrayList<Contributor> tmpContributor = getProjectContributors(currentProject);

            if(tmpContributor == null){
                pIdx++;
                continue;
            }

            if(tmpContributor != null) {
                currentProject.contributors = tmpContributor;
                currentProject.startDay = currentDay;
                for (Contributor c: currentProject.contributors)
                    c.isBusy = true;
            }



            // Giorno successivo
            currentDay++;


            if(currentDay > 2)
                break;
        }
    }

    public ArrayList<Contributor>  getProjectContributors(Project project){

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
        String output = completedProjects.size() + "\n";

        for (Project p : completedProjects) {

            output = output + p.name + "\n";

            for (Contributor c : p.contributors) {

                output = output + c.name + " ";
            }

            output = output + "\n";
        }
        return output;
    }


    public String toString(){

        return currentDay + " \n" + projects.toString() + " \n" + contributors.toString();
    }
}
