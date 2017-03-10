package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());//done. part of the 1st task tbd. sends the call and activates
                    //with 0 from -List->0-All occurs.
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                    //System.out.println("coming from main-->Search all fields in progress.");
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            //System.out.println("choice keys are:"+choiceKeys[i]);
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " -" + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {//somejobs is of type
        //arraylist<<hashmap>>
                //i added use this ------>>>>>> public static ArrayList<HashMap<String, String>> findAll()
                Boolean choice1 = false;

        // ArrayList<HashMap<String, String>> printalljobs   = new ArrayList<>();
        //printalljobs=someJobs;
        //HashMap<String, String> newjob1 = new HashMap<>();
        //String []

        int count=1;
        for (HashMap<String, String> hash : someJobs){
            choice1=true;
            {//System.out.println(hash.keySet()+" <-key,values-> "+hash.values()+"hash size is: "+hash.size
               // ());
                System.out.println("Job list# "+count++);
                System.out.println("**********");
                for (Map.Entry<String, String> subhash : hash.entrySet()) {
                    System.out.println(subhash.getKey() + " :" + subhash.getValue() );
                }
                System.out.println("**********\n");
            }

        }
        if (choice1=true)
            System.out.println("\n##################");
        System.out.println(((int)count-1)+" Job listings found");
        System.out.println("##################");

        if (choice1=false)
            System.out.println("Search yields no result. Please try again. printJobs is not done!");
    }
}

