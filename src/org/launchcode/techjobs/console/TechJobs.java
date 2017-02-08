package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    private static String[] actionChoices = {"Browse", "Search"};
    private static String[] browserChoices = {"Skill", "Employer"};
    private static String[] searchChoices = {"Skill", "Employer"};

    public static void main (String[] args) {

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow user to search until they manually quit
        while (true) {

            Integer actionChoice = displayChoiceMenu("View jobs", actionChoices);

            if (actionChoices[actionChoice].equals("Browse")) {

                Integer browseChoice = displayChoiceMenu("Browse", browserChoices);

                if (browserChoices[browseChoice].equals("Employer")) {
                    ArrayList<String> allEmployers = JobData.getAllEmployers();

                    System.out.println("\n*** All employers ***");

                    for (String employer : allEmployers) {
                        System.out.println(employer);
                    }

                } else {
                    ArrayList<String> allSkills = JobData.getAllSkills();

                    System.out.println("\n*** All skills ***");

                    for (String skill : allSkills) {
                        System.out.println(skill);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                Integer searchChoice = displayChoiceMenu("Search", searchChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                ArrayList<HashMap<String, String>> searchResults;

                // Fetch and print results
                if (searchChoices[searchChoice].equals("Skill")) {
                    searchResults = JobData.getJobsBySkill(searchTerm);
                } else {
                    searchResults = JobData.getJobsByEmployer(searchTerm);
                }

                if (searchResults.size() == 0) {
                    System.out.println("No results");
                } else {
                    printJobs(searchResults);
                }
            }

        }

    }

    // Displays a console menu offering choices from the parameter array choices.
    // Input is validated and returned to the caller, once a valid choice is made.
    private static Integer displayChoiceMenu(String choiceText, String[] choices) {

        Integer result;
        Boolean validChoice = false;

        do {

            System.out.println("\n" + choiceText + " by: ");

            // Print available choices
            for (Integer i = 0; i < choices.length; i++) {
                System.out.println("" + i + " - " + choices[i]);
            }

            result = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (result < 0 || result >= choices.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return result;
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        for (HashMap<String, String> job : someJobs) {

            String jobString = "\n*****" +
                    "\nEmployer: " + job.get("employer") +
                    "\nName: " + job.get("name") +
                    "\nLocation: " + job.get("location") +
                    "\nSkills: " + job.get("core competency") +
                    "\n*****";

            System.out.println(jobString);
        }
    }
}
