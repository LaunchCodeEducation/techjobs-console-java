package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> fieldChoices = new HashMap<>();
        fieldChoices.put("core competency", "Skill");
        fieldChoices.put("employer", "Employer");
        fieldChoices.put("position type", "Position Type");
        fieldChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        // Initialize keys array
        String[] fieldNames = new String[fieldChoices.size()];
        Integer i = 0;
        for (String value : fieldChoices.values()) {
            fieldNames[i] = value;
            i++;
        }

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String browseChoice = getUserSelection("List", fieldChoices);
                ArrayList<String> browseList = JobData.findAll(browseChoice);

                if (browseChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {
                    System.out.println("\n*** All " + fieldChoices.get(browseChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : browseList) {
                        System.out.println(item);
                    }
                }



            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", fieldChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    System.out.println("Search all fields not yet implemented.");
                } else {
                    printJobs(JobData.findByKeyAndValue(searchField, searchTerm));
                }
            }

        }

    }

    // Displays a console menu offering choices from the parameter array choices.
    // Input is validated and returned to the caller, once a valid choice is made.
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
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
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        System.out.println("printJobs is not implemented yet");
    }
}
