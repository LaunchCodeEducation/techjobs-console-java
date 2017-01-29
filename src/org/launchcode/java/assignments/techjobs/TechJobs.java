package org.launchcode.java.assignments.techjobs;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    public static void main (String[] args) {
        ArrayList<String> skills = JobData.getSkills();

        for (String s : skills) {
            System.out.println(s);
        }
    }
}
