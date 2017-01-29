package org.launchcode.java.assignments.gradebook.tests;

import org.junit.Test;
import org.launchcode.java.assignments.gradebook.Course;
import org.launchcode.java.assignments.gradebook.Student;

import static org.junit.Assert.*;

/**
 * Created by LaunchCode
 */
public class CourseTest {

    @Test
    public void testCourseInit() {
        Course c = new Course("CSE131", 1, 2);
        assertEquals("CSE131", c.getName());
        assertEquals(2, c.getRemainingSeats());
        for (int i = 0; i < 20; ++i) {
            double a =  Math.random() * 5000;
            int s = (int) (Math.random() * 5000);
            Course c2 = new Course("" + a, s, s);
            assertEquals(String.valueOf(a), c2.getName());
            assertEquals(s, c2.getRemainingSeats());
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
                int cc = (int) (Math.random() * 500000);
                Student s2 = new Student(aa, b, cc);
                boolean added = c.addStudent(s2);
                assertTrue(added);

                assertEquals(s - j - 1, c.getRemainingSeats());
            }

            //Try to add students, even though the class is full.
            for (int j = 0; j < s; j++) {
                String aa =  "" + (Math.random() * 5000);
                String b =  "" + (Math.random() * 5000);
                int cc = (int) (Math.random() * 500000);
                Student s2 = new Student(aa, b, cc);
                boolean added = c.addStudent(s2);

                assertTrue(!added);
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
                assertEquals(gpaSum / (i + 1), c.averageGPA(), 0.01);
            }
        }
    }

    @Test
    public void testCourseToString() {
        for (int i = 0; i < 100; i++) {
            double a =  (Math.random() * 5000);
            int c = (int) (Math.random() * 500000);
            Course cc = new Course("" + a, c, c);
            assertTrue(cc.toString().contains("" + a));
            assertTrue(cc.toString().contains("" + c));
        }
    }
}
