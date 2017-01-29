package org.launchcode.java.assignments.gradebook.tests;

import org.junit.Test;
import org.launchcode.java.assignments.gradebook.Student;

import static org.junit.Assert.*;


/**
 * Created by LaunchCode
 */
public class StudentTest {

    private static double delta = 0.01;

    @Test
    public void testStudentInit() {
        Student s = new Student("Doug", "Shook", 111111);
        assertEquals("Doug Shook", s.getName());
        assertEquals(111111, s.getStudentID());
        //No credits, should be a freshman, no GPA
        assertEquals(0.0, s.getGPA(), delta);
        assertEquals(0, s.getCredits());
        assertEquals("Freshman", s.getClassStanding());
        for (int i = 0; i < 20; ++i) {
            double a =  (Math.random() * 5000);
            double b =  (Math.random() * 5000);
            int c = (int)(Math.random() * 5000);
            Student s3 = new Student("" + a, "" + b, c);
            assertEquals(a + " " + b, s3.getName());
            assertEquals(0.0, s3.getGPA(), delta);
            assertEquals(0, s3.getCredits());
            assertEquals("Freshman", s3.getClassStanding());
        }
    }

    @Test
    public void testGetClassStanding() {
        Student s = new Student("D", "S", 1);
        for (int i = 0; i < 29; i++) {
            s.submitGrade(1.0, 1);
            assertEquals("Freshman", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals("Sophomore", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals("Junior", s.getClassStanding());
        }

        for (int i = 0; i < 30; i++) {
            s.submitGrade(1.0, 1);
            assertEquals("Senior", s.getClassStanding());
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
            assertEquals((i+1) * 1333.33, s.computeTuition(), delta);
        }

        s.submitGrade(0, 1);
        assertEquals(20000.0, s.computeTuition(), delta);

        for (int i = 0; i < 14; i++) {
            s.submitGrade(0, 1);
            assertEquals(1333.33 * (i+1) + 20000.0, s.computeTuition(), delta);
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
            assertTrue(bb.getName().contains(s.getName()) && bb.getName().contains(ss.getName()));
            assertEquals((g + g2) / 2, bb.getGPA(), 0.01);
            assertEquals(bb.getCredits(), Math.max(c, c2));
            assertTrue(bb.getStudentID() == s.getStudentID() + ss.getStudentID());

            //Make sure parents haven't changed
            assertEquals(a + " " + b, s.getName());
            assertEquals(g, s.getGPA(), delta);
            assertEquals(c, s.getCredits());

            assertEquals(a2 + " " + b2, ss.getName());
            assertEquals(g2, ss.getGPA(), delta);
            assertEquals(c2, ss.getCredits());

        }
    }

    @Test
    public void testStudentToString() {
        for (int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            double b =  (Math.random() * 5000);
            int c = (int) (Math.random() * 500000);
            Student s = new Student("" + a, "" + b, c);
            assertTrue(s.toString().contains(String.valueOf(a)));
            assertTrue(s.toString().contains(String.valueOf(b)));
            assertTrue(s.toString().contains(String.valueOf(c)));
        }
    }

}
