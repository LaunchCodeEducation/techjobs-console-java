package org.launchcode.java.exercises.gradebook;

/**
 * Created by LaunchCode
 */

public class Course {

    private String name;
    private int credits;
    private int remainingSeats;
    private Student[] roster;

    public Course(String name, int credits, int numberOfSeats) {
        this.roster = new Student[numberOfSeats];
        this.name = name;
        this.credits = credits;
        this.remainingSeats = numberOfSeats;
    }

    public String getName() {
        return name;
    }
    public int getCredits() {
        return credits;
    }
    public int getRemainingSeats() {
        return remainingSeats;
    }
    public Student[] getRoster() {
        return roster;
    }

    public Boolean addStudent(Student newStudent) {

        if (this.remainingSeats == 0) {
            return false;
        }

        // check to make sure student hasn't already enrolled
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null && roster[i].getName() == newStudent.getName()) {
                return false;
            }
        }

        roster[roster.length - this.remainingSeats] = newStudent;
        remainingSeats--;

        return true;
    }

    public double averageGPA() {

        double sum = 0.0;
        int numberOfStudents = 0;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                sum += roster[i].getGPA();
                numberOfStudents++;
            }
        }

        return sum / numberOfStudents;
    }

    public String generateRoster() {

        String rosterString = "";
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                rosterString = rosterString + roster[i].getName() + "\n";
            }
        }

        return rosterString;
    }

    public String toString() {
        return this.name + "(" + this.credits + ")";
    }

}
