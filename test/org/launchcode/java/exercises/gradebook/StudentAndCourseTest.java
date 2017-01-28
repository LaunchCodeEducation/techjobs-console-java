package org.launchcode.java.exercises.gradebook;

import​ static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Test;


/**
 * Created by LaunchCode
 */
public class StudentAndCourseTest {

    String s1 = "either your getName() method isn't doing its job or you're not " +
            "setting your instance variables properly";
    String s2 = "either your getGPA() method is not doing its job or you're not " +
            "setting your instance variables properly";
    String s4 = "either your getCredits() method is not doing its job or you're not " +
            "setting your instance variables properly";
    String s5 = "either your getClassStanding() method isn't doing its job or you're not " +
            "setting your instance variables properly";
    String s6 = "The plus method shouldn't change the original vector";
    String s8 = "hasHigherAverage() isn't working properly";
    String s9 = "getClassStanding() isn't working properly";
    @Test
    public void testStudentInit() {
        Student s = new Student("Doug", "Shook", 111111);
        assertEquals("Doug Shook", s.getName());
        assertEquals(111111, s.getStudentID());
        //No credits, should be a freshman, no GPA
        assertEquals(0.0, s.getGPA());
        assertEquals(0, s.getCredits());
        assertEquals("Freshman", s.getClassStanding());
        for (int i = 0; i < 20; ++i) {
            double a =  (Math.random() * 5000);
            double b =  (Math.random() * 5000);
            int c = (int)(Math.random() * 5000);
            Student s3 = new Student("" + a, "" + b, c);
            assertEquals(s1, a + " " + b, s3.getName());
            assertEquals(s2, 0.0, s3.getGPA());
            assertEquals(s4, 0, s3.getCredits());
            assertEquals(s5, "Freshman", s3.getClassStanding());
        }
    }

    @Test
    public void testGetClassStanding() {
        Student s = new Student("D", "S", 1);
        for (int i = 0; i < 29; i++) {
            s.submitGrade(1.0, 1);
            assertEquals(s9, "Freshman", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals(s9, "Sophomore", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals(s9, "Junior", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals(s9, "Senior", s.getClassStanding());
        }

    }

    @Test
    public void testSubmitGrade() {
        Student s = new Student("D", "S", 1);
        int credits = 0;
        double gpatotal = 0;
        for (int i = 0; i < 100; i++) {
            int c = (int)(Math.random() * 3 + 1);//1 to 3 credits
            double g = Math.random() * 4;//0 to 4
            credits += c;
            gpatotal += g * c;
            s.submitGrade(g, c);
            assertEquals("GPA computed incorrectly", gpatotal / credits, s.getGPA(), 0.01);
            assertTrue("GPA not rounded", (s.getGPA() + "").length() < 6);
        }
    }

    @Test
    public void testComputeTuition() {
        Student s = new Student("D", "S", 1);
        for (int i = 0; i < 14; i++) {
            s.submitGrade(0, 1);
            assertEquals("Compute tution not working properly", (i+1) * 1333.33, s.computeTuition());
        }

        s.submitGrade(0, 1);
        assertEquals("Compute tution not working properly", 20000.0, s.computeTuition());

        for (int i = 0; i < 14; i++) {
            s.submitGrade(0, 1);
            assertEquals("Compute tution not working properly", 1333.33 * (i+1) + 20000.0, s.computeTuition());
        }
    }

    @Test
    public void testCreateLegacy() {
        for(int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            double b =  (Math.random() * 5000);
            Student s = new Student("" + a, "" + b, 1);
            double a2 =  (Math.random() * 5000);
            double b2 =  (Math.random() * 5000);
            Student ss = new Student("" + a2, "" + b2, 2);
            int c = (int)(Math.random() * 120 + 1);
            double g = Math.round(Math.random() * 4000) / 1000.0;
            s.submitGrade(g, c);
            int c2 = (int)(Math.random() * 120 + 1);
            double g2 = Math.round(Math.random() * 4000) / 1000.0;
            ss.submitGrade(g2, c2);
            Student bb = s.createLegacy(s, ss);
            assertTrue("create baby not setting name properly", bb.getName().contains(s.getName()) && bb.getName().contains(ss.getName()));
            assertEquals("create baby not setting gpa properly", (g + g2) / 2, bb.getGPA(), 0.01);
            assertEquals("create baby not setting credits properly", bb.getCredits(), Math.max(c, c2));
            assertTrue("create baby not creating ID properly", bb.getStudentID() == s.getStudentID() + ss.getStudentID());

            //Make sure parents haven't changed
            assertEquals("createBaby should not alter the parents", a + " " + b, s.getName());
            assertEquals("createBaby should not alter the parents", g, s.getGPA());
            assertEquals("createBaby should not alter the parents", c, s.getCredits());

            assertEquals("createBaby should not alter the parents", a2 + " " + b2, ss.getName());
            assertEquals("createBaby should not alter the parents", g2, ss.getGPA());
            assertEquals("createBaby should not alter the parents", c2, ss.getCredits());

        }
    }

    @Test
    public void testStudentToString() {
        for (int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            double b =  (Math.random() * 5000);
            int c = (int)Math.random() * 500000;
            Student s = new Student("" + a, "" + b, c);
            assertTrue("student toString does not contain entire student name", s.toString().contains("" + a));
            assertTrue("student toString does not contain entire student name", s.toString().contains("" + b));
            assertTrue("student toString does not contain student ID", s.toString().contains("" + c));
        }
    }





    // TESTING COURSE CLASS HERE . . . FEEL FREE TO WRITE YOUR OWN, BUT DON'T CHANGE THIS ONE
    // once again, we are watching you

    @Test
    public void testCourseInit() {
        Course c = new Course("CSE131", 1, 2);
        assertEquals("CSE131", c.getName());
        assertEquals(2, c.getRemainingSeats());
        for (int i = 0; i < 20; ++i) {
            double a =  (Math.random() * 5000);
            int s = (int)(Math.random() * 5000);
            Course c2 = new Course("" + a, s, s);
            assertEquals("course getName() not working, or name not being set properly", "" + a, c2.getName());
            assertEquals("course getRemainingSeats() not working, or seats not being set properly", s, c2.getRemainingSeats());
        }
    }

    @Test
    public void testAddStudent() {
        for (int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            int s = (int)(Math.random() * 50);
            Course c = new Course("" + a, s, s);

            for (int j = 0; j < s; j++) {
                String aa =  "" + (Math.random() * 5000);
                String b =  "" + (Math.random() * 5000);
                int cc = (int)Math.random() * 500000;
                Student s2 = new Student(aa, b, cc);
                boolean added = c.addStudent(s2);
                assertTrue("addStudent not working properly", added);

                assertEquals("seats not updated after adding a student", s - j - 1, c.getRemainingSeats());
            }

            //Try to add students, even though the class is full.
            for (int j = 0; j < s; j++) {
                String aa =  "" + (Math.random() * 5000);
                String b =  "" + (Math.random() * 5000);
                int cc = (int)Math.random() * 500000;
                Student s2 = new Student(aa, b, cc);
                boolean added = c.addStudent(s2);

                assertTrue("addStudent not working properly: student added even though class was full", !added);
            }
        }
    }

    @Test
    public void testAverageGPA() {
        for (int j = 0; j < 100; j++) {
            double a =  (Math.random() * 5000);
            int s = (int)(Math.random() * 50 + 50);
            Course c = new Course("" + a, s, s);

            double gpaSum = 0;
            for(int i = 0; i < s; i++) {
                String aa =  "" + (Math.random() * 5000);
                String b =  "" + (Math.random() * 5000);
                int cc = (int)(Math.random() * 500000);
                Student s2 = new Student(aa, b, cc);
                double g = Math.round(Math.random() * 4000) / 1000.0;
                gpaSum += g;
                s2.submitGrade(g, 1);
                c.addStudent(s2);
                assertEquals("course averageGPA() not working properly", gpaSum / (i + 1), c.averageGPA(), 0.01);
            }
        }
    }

    @Test
    public void testCourseToString() {
        for (int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            int c = (int)Math.random() * 500000;
            Course cc = new Course("" + a, c, c);
            assertTrue("course toString does not contain course name", cc.toString().contains("" + a));
            assertTrue("course toString does not contain credits", cc.toString().contains("" + c));
        }
    }



}
