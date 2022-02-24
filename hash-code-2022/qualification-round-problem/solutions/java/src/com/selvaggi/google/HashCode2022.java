package com.selvaggi.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HashCode2022 {


    /**
     * Read input file
     *
     * @param filePath
     * @param fileName
     * @return ArrayList with the input structured
     */
    private static World readInputFile(String filePath, String fileName) {
        System.out.println("Read file named " + fileName);
        World world = new World();
        //ArrayList<Pizza> pizzas = new ArrayList<>();

        //streets = new ArrayList<>();

        try {
            File myObj = new File(filePath + fileName);
            System.out.println("File size in bytes " + myObj.length());
            Scanner myReader = new Scanner(myObj);

            int contributors = myReader.nextInt();
            int projects = myReader.nextInt();

            System.out.println("Contributor: " + contributors + " Projects: " + projects);
            myReader.nextLine();
            for(int i = 0; i < contributors; i++){
                String contributorLine = myReader.nextLine();
                Scanner myLine = new Scanner(contributorLine);

                String contributorName = myLine.next();
                int contributorSkills = myLine.nextInt();

                Contributor contributor = new Contributor(contributorName, false);
                //System.out.println("Contributor Name: " + contributorName);

                for(int j = 0; j < contributorSkills; j++){
                    Scanner skillLine = new Scanner(myReader.nextLine());
                    String skillLineName = skillLine.next();
                    int skillLevel = skillLine.nextInt();
                    //System.out.println("Skill Name: " + skillLineName);
                    Skill skill = new Skill(skillLineName, skillLevel);
                    //System.out.println(skill.toString());
                    contributor.skills.add(skill);
                }

                world.contributors.add(contributor);

                //System.out.println(contributor.toString());
            }

            // Projects
            for(int i = 0; i < projects; i++){
                String projectsLine = myReader.nextLine();
                Scanner myLine = new Scanner(projectsLine);

                String projectName = myLine.next();
                int projectDays = myLine.nextInt();
                int projectScore = myLine.nextInt();
                int projectBest = myLine.nextInt();
                int projectRoles = myLine.nextInt();

                /*System.out.println("Project Name: " + projectName + " Day " + projectDays + " Score " + projectScore +
                        " Best: " + projectBest + " Roles: " + projectRoles);*/

                Project project = new Project(projectName, projectDays, projectScore, projectBest, projectRoles);

                for(int j = 0; j < projectRoles; j++){
                    Scanner skillLine = new Scanner(myReader.nextLine());
                    String skillLineName = skillLine.next();
                    int skillLevel = skillLine.nextInt();
                    //System.out.println("Skill Name: " + skillLineName);
                    Skill skill = new Skill(skillLineName, skillLevel);
                    //System.out.println(skill.toString());
                    project.skills.add(skill);
                }
                //System.out.println(project.toString());
                world.projects.add(project);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not found! " + filePath + fileName);
        }
        return world;
    }

    private static void writeOutputFile(String outPath, String fileName, String outputBody) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(outPath + fileName + "_out.txt");

            myWriter.write(outputBody);

            myWriter.close();
            System.out.println("Successfully wrote to the file: " + outPath + fileName + "_out.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String filePath = "../../input_files/";
        String outPath = "../../output/";
        String fileName = "a.txt";

        if (args.length > 0)
            fileName = args[0];

        // Read the input file
        World world = readInputFile(filePath, fileName);

        System.out.println(world.toString());

        world.elaborate();

        System.out.println("Final Score: " + world.getScore());

        // Write the file
        writeOutputFile(outPath, fileName, world.getOutput());
    }
}

