package com.selvaggi.google;

import java.util.*;

public class World {

    public int MAX_DAY = 10000;

    public int currentDay;
    public ArrayList<Project> projects;
    public ArrayList<Contributor> contributors;
    public ArrayList<Project> completedProjects;
    //public ArrayList<Project> workingProjects;
    public int projectsInProgress;
    public int projectsDone;

    public World() {
        this.projects = new ArrayList<>();
        this.contributors = new ArrayList<>();
        this.completedProjects = new ArrayList<>();
        //this.workingProjects = new ArrayList<>();
        this.projectsInProgress = 0;
        this.projectsDone = 0;
    }

    public void elaborate(){

        Collections.sort(projects, new Comparator<Project>() {
            @Override
            public int compare(Project p1, Project p2)
            {
                return  p1.bestBefore - p2.bestBefore;
            }
        });

        while (projectsDone < projects.size()  && currentDay <= MAX_DAY){

            // Check complete projects
            for (Project p: projects) {
                if(p.workInProgress && p.isCompleted(currentDay)){

                    projectsDone++;
                    projectsInProgress--;
                    p.isDone = true;
                    p.workInProgress = false;
                    completedProjects.add(p);

                    for (int i = 0; i< p.skills.size(); i++){
                        p.contributors.get(i).upgradeSkill(p.skills.get(i));
                    }
                    for (Contributor c:p.contributors) {
                        c.isBusy = false;
                    }
                }
            }


            // Find if the project can be worked
            for(Project currentProject: projects){

                if(!currentProject.isDone && !currentProject.workInProgress){

                    ArrayList<Contributor> tmpContributor = getProjectContributors(currentProject);
                    if(tmpContributor == null){
                        continue;
                    }

                    if(tmpContributor != null) {
                        currentProject.contributors = tmpContributor;
                        currentProject.startDay = currentDay;
                        currentProject.workInProgress = true;
                        projectsInProgress++;
                        for (Contributor c: currentProject.contributors)
                            c.isBusy = true;

                    }
                }

            }

            // Giorno successivo
            currentDay++;
            System.out.print("\r Oggi è: " + currentDay);
        }
    }

    public ArrayList<Contributor>  getProjectContributors(Project project){

        ArrayList<Contributor> tmpContr = new ArrayList<>();

        for (Skill s:project.skills ) {

            //find contributor
            Contributor c = findContributor(s);
            if(c == null)
                break;

            tmpContr.add(c);
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
        String output = projectsDone + "";

        for (Project p : completedProjects) {

            if(p.isDone){

                output = output +"\n"+ p.name + "\n";


                for (Contributor c : p.contributors) {

                    output = output + c.name + " ";

                }

                output = output .trim();
            }
        }

        return output;
    }


    public String toString(){

        return currentDay + " \n" + projects.toString() + " \n" + contributors.toString();
    }
}
