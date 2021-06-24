package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

        String intro = "\n\nWelcome to LaunchCode's TechJobs App!";
        String colorizedIntro =  CustomFormatter.colorWrap(intro, CustomFormatter.BLUE_BOLD_BRIGHT);
        System.out.println(colorizedIntro);

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);
                    String header = "\n*** All " + columnChoices.get(columnChoice) + " Values ***";
                    String colorizedHeader = CustomFormatter.colorWrap(header, CustomFormatter.WHITE_BOLD_BRIGHT);
                    System.out.println(colorizedHeader);

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(CustomFormatter.colorWrap(item, CustomFormatter.GREEN_BRIGHT));
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
        if (someJobs.size() == 0) {
            System.out.println("No jobs match the selected criteria.");
            return;
        }

        String asteriks = CustomFormatter.colorWrap("**************", CustomFormatter.WHITE_BOLD_BRIGHT);
        String headerTemplate = asteriks + "%s";
        String footerTemplate = asteriks + "%s";;
        String rowTemplate = "%s: %s";
        Integer jobCounter = 0;

        System.out.println();

        for (HashMap<String, String> job: someJobs) {
            jobCounter += 1;
            String colorizedResult = CustomFormatter.colorWrap("\nResult #" + jobCounter, CustomFormatter.BLUE_BRIGHT);
            String header = String.format(headerTemplate, colorizedResult);
            String footer = String.format(footerTemplate, "\n");

            System.out.println(header);

            for (Map.Entry<String, String> entry : job.entrySet()) {
                String titleCasedKey = CustomFormatter.titleCase(entry.getKey());
                String colorizedKey = CustomFormatter.colorWrap(titleCasedKey, CustomFormatter.WHITE_BOLD_BRIGHT);
                String colorizedValue = CustomFormatter.colorWrap(entry.getValue(), CustomFormatter.GREEN_BOLD_BRIGHT);
                String formattedString = String.format(rowTemplate, colorizedKey, colorizedValue);
                System.out.println(formattedString);
            }

            System.out.println(footer);
        }
    }
}