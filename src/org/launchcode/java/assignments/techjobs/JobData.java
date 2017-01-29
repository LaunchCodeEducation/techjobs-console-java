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

    private static ArrayList<HashMap<String, String>> allJobs;

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


            allJobs = new ArrayList<>();

            for (CSVRecord record : records) {
                HashMap<String, String> newJob = new HashMap<>();

                for (Integer i = 0; i < headers.length; i++) {
                    String headerLabel = headers[i];
                    newJob.put(headerLabel, record.get(headerLabel));
                }

                allJobs.add(newJob);
            }

            dataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
    }

    /**
     * Fetch list of all employers from loaded data,
     * without duplicates.
     *
     * @return List of all employers with jobs listed
     */
    public static ArrayList<String> getEmployers() {

        loadData();

        ArrayList<String> employers = new ArrayList<>();

        for (HashMap<String, String> job : allJobs) {
            String newEmployer = job.get("employer");

            if (!employers.contains(newEmployer)) {
                employers.add(newEmployer);
            }
        }

        return employers;
    }

    /**
     * Returns results of search the jobs data by employer, using
     * inclusion of the search term.
     *
     * For example, searching for "Enterprise" will include results
     * with "Enterprise Holdings, Inc" as the employer.
     *
     * @param employer  Name of an employer
     * @return List of all jobs listed by the employer
     */
    public static ArrayList<HashMap<String, String>> getJobsByEmployer(String employer) {

        loadData() ;

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> job : allJobs) {

            String anEmployer = job.get("employer");

            if (anEmployer.contains(employer)) {
                jobs.add(job);
            }
        }

        return jobs;
    }

    /**
     * Fetch list of all employers from loaded data,
     * without duplicates.
     *
     * @return List of all employers with jobs listed
     */
    public static ArrayList<String> getSkills() {

        loadData();

        ArrayList<String> skills = new ArrayList<>();

        for (HashMap<String, String> job : allJobs) {
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

    /**
     * Returns results of search the jobs data by skill, using
     * inclusion of the search term.
     *
     * For example, searching for "HTML" will include results
     * with "HTML, JS, CSS" in the skills field.
     *
     * @param skill  Name of an skill
     * @return List of all jobs listed containing the given skill
     */
    public static ArrayList<HashMap<String, String>> getJobsBySkill(String skill) {

        loadData();

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> job : allJobs) {

            String jobSkills = job.get("skills");

            if (jobSkills.contains(skill)) {
                jobs.add(job);
            }
        }

        return jobs;
    }

}
