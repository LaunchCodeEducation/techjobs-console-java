package org.launchcode.java.assignments.techjobs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
public class JobData {

    private static final String DATA_FILE = "resources/job_data.csv";
    private static Boolean dataLoaded = false;
    private static Integer numberOfColumns;

    private static ArrayList<HashMap<String, String>> jobs;

    private static void loadData() {

        // Only load data once
        if (dataLoaded) {
            return;
        }

        try {
            Reader in = new FileReader(DATA_FILE);

            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);


            jobs = new ArrayList<>();

            for (CSVRecord record : records) {
                HashMap<String, String> newJob = new HashMap<>();

                for (Integer i = 0; i < headers.length; i++) {
                    String headerLabel = headers[i];
                    newJob.put(headerLabel, record.get(headerLabel));
                }

                jobs.add(newJob);
            }

            dataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getEmployers() {

        loadData();

        ArrayList<String> employers = new ArrayList<>();

        for (HashMap<String, String> job : jobs) {
            String newEmployer = job.get("employer");

            if (!employers.contains(newEmployer)) {
                employers.add(newEmployer);
            }
        }

        return employers;
    }

    public static ArrayList<String> getJobsByEmployer(String employer) {

        loadData() ;

        // TODO
        return null;
    }

    public static ArrayList<String> getSkills() {

        loadData();

        ArrayList<String> skills = new ArrayList<>();

        for (HashMap<String, String> job : jobs) {
            String skillsList = job.get("skills");
            String[] individualSkills = skillsList.split(",");

            for (Integer i=0; i < individualSkills.length; i++) {
                String newSkill = individualSkills[i].trim();
                if (!skills.contains(newSkill)) {
                    skills.add(newSkill);
                }
            }
        }

        return skills;
    }

    public static ArrayList<String> getJobsBySkill(String skill) {

        loadData();

        // TODO
        return null;
    }

}
