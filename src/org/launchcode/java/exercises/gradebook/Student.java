package org.launchcode.java.exercises.gradebook;

/**
 * Created by LaunchCode
 */

public class Student {

    private String name;
    private int studentID;
    private int credits;
    private double GPA;

    public Student(String firstName, String lastName, int studentID) {
        this.name = firstName + " " + lastName;
        this.studentID = studentID;
        this.credits = 0;
        this.GPA = 0.0;
    }

    public Student(String firstName, String lastName, int studentID, int credits, double GPA) {
        this.name = firstName + " " + lastName;
        this.studentID = studentID;
        this.credits = credits;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }
    public int getCredits() {
        return credits;
    }
    public double getGPA() {
        return GPA;
    }

    public String getClassStanding() {

        if (this.credits < 30) {
            return "Freshman";
        } else if (this.credits < 60) {
            return "Sophomore";
        } else if (this.credits < 90) {
            return "Junior";
        } else {
            return "Senior";
        }

    }

    public void submitGrade(double courseGrade, int courseCredits) {

        // compute new GPA
        double qualityScore = courseGrade * courseCredits;
        double qualitySubtotal = this.GPA * this.credits;
        this.credits += courseCredits;
        qualitySubtotal += qualityScore;
        double unrounded = qualitySubtotal / this.credits;

        // round to 3 decimal places
        this.GPA = (double) Math.round(unrounded * 1000.0) / 1000.0;
    }

    public double computeTuition() {
        int semesters = this.credits / 15;
        int leftoverCredits = this.credits % 15;
        return semesters * 20000.0 + leftoverCredits * 1333.33;
    }

    public static Student createLegacy(Student firstParent, Student secondParent) {
        return new Student(
                firstParent.getName(),
                secondParent.getName(),
                firstParent.getStudentID() + secondParent.getStudentID(),
                Math.max(firstParent.getCredits(), secondParent.getCredits()),
                (firstParent.getGPA() + secondParent.getGPA()) / 2);
    }

    public String toString() {
        return this.name + "(" + this.studentID + ")";
    }
}
